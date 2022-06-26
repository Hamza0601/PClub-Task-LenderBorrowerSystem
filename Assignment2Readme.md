# Assignment 2
## How to use the contract 
In the Remix IDE , after setting the owner account deploy the contract. The list of the following functions will be visible:  
  
- reqLoan (to be used by the creditor) :anyone will be able to use it to request the Owner to settle his loan. The P, R, T entered will be used to calculate the dues, and is added to a mapping
- sendCoin ( derived from Metacoin)
- settleDues (can only be used by the Owner of the contract) :only the owner will be able to use this to settle the amount of loan he owes to the input address, using MetaCoin's sendCoin function to settle these dues, with appropriate checks for the return values from sendCoin. If it runs succesfully then the owed amount will be modified to 0
- getBalance (view function) (derived from Metacoin): to let the address see the loan amount
- getCompoundInterest (pure function): calculates the compound interest
- getOwnerBalance (view function):the owner will be able to see the amount remaining in his account
- mulDiv (pure function)
- viewDues (view function):can only be used by the Owner of the contract
From the account dropdown , you can change account to creditor for using reqLoan.  
### Implementation of function getMaxAddress
Declared two state variables maximumAddress and maximumLoan
Every time the function reqLoan is called , the value of maximumAddress and maximumLoan gets updated.
The function returns the current value of maximumAddress
### Implementation of function getMaxAddress2
In the array containing addresses of creditors who called the reqLoan function, the getMaximumAddress2 function will iterate through the array and find the maximum every time it's called upon.
