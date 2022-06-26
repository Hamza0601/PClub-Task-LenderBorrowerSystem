// SPDX-License-Identifier: MIT
pragma solidity >=0.4.25 <0.7.0;
contract MetaCoin {
	mapping (address => uint256) balances;

	event Transfer(address indexed _from, address indexed _to, uint256 _value);

	constructor() public {
		balances[tx.origin] = 100000;
	}

	function sendCoin(address receiver, uint256 amount, address sender) public returns(bool sufficient) {
		if (balances[sender] < amount) return false;
		balances[sender] -= amount;
		balances[receiver] += amount;
		emit Transfer(sender, receiver, amount);
		return true;
	}


	function getBalance(address addr) public view returns(uint256) {
		return balances[addr];
	}
}
contract Loan is MetaCoin {

    mapping (address => uint256) private loans;
    address[] creditors;
    event Request(address indexed _from, uint256 P, uint R, uint T, uint256 amt);
    
    address private Owner;
    uint256 maximumLoan=0;
    address maximumAddress;
    modifier isOwner() {
        require(msg.sender == Owner, "Caller is not owner");
        _;
    }
    constructor() public {
        Owner=msg.sender;
    }   
    function mulDiv(uint256 n1, uint256 n2, uint256 n3)public pure returns(uint){
        uint256 a = n1 / n3;
        uint256 b = n1 % n3; 
        uint256 c = n2 / n3; 
        uint256 d = n2 % n3; 
        return a * b * n3 + a * d + b * c + b * d / n3;
    }
    function getCompoundInterest(uint256 principle, uint rate, uint time) public pure returns(uint256) {
    		
        uint256 P = principle*1e18;
        uint256 R = rate;
        uint256 divider=100;
        while(time>0)
        {
            P+=mulDiv(P,R,divider);
            time--;
        }
        return P/1e18;
    }
    
    
    function reqLoan(uint256 principle, uint rate, uint time) public returns(bool correct) {
        uint256 toPay = getCompoundInterest(principle, rate, time);
         if(toPay>principle){
            loans[msg.sender]=toPay;
            creditors.push(msg.sender);
            if(toPay>maximumLoan)
            {
                maximumLoan=toPay;
                maximumAddress=msg.sender;
            }
            emit Request(msg.sender,principle,rate,time,toPay);
            return true;
        }
        else 
            return false;
       
    }
    
    
    function getOwnerBalance() public view returns(uint256) {
				return super.getBalance(Owner);
		}
    function viewDues(address creditor) isOwner public view returns(uint256){
        return loans[creditor];
    }
     function settleDues(address creditor) isOwner public  returns (bool){
        if(super.sendCoin(creditor,loans[creditor],Owner))
        {
            loans[creditor]=0;
            return true;
        }
        else
        {
            return false;
        }
    }
    function getMaximumAddress() public view returns(address){
        return maximumAddress;
    }
    function getMaximumAddress2() public view returns(address){
        address maximumAddress2;
        uint256 maxLoan2=0;
        for(uint i=0;i<creditors.length;i++)
        {
            if(maxLoan2<loans[creditors[i]])
            {
                maxLoan2=loans[creditors[i]];
                maximumAddress2=creditors[i];
            }
        }
        return maximumAddress2;
    }    
}
