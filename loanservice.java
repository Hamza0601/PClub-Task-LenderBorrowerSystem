import java.util.*;
import java.lang.Math;

class Lender
{
    // instance variables - replace the example below with your own
    double base_rate; // the base_rate that the lender will charge the borrower with credit score 0, i.e, newcomer
    int id; // lender_id
    String name, contact, address; // name and contact number of lender
    char interest_type; // whether he charges simple interest or compound interest
    ArrayList<Integer> borrowers; // list of people who have pending amount left to be paid to the lender
    HashMap<Integer, Double> moneyleft; // mapping the borrowerId to the amount left for the borrower to pay

    public Lender(String nm, String phno, double br)
    {
        name = nm;
        base_rate = br;
        contact = phno;
        id = (int)(Math.random() * (100000 - 10000 + 1) + 10000); // 4-digit ids assigned
        interest_type = 'S'; // byte default simple interest
        address = "NA"; // not given
        borrowers = new ArrayList<Integer>();
        moneyleft = new HashMap<Integer, Double>();
    }

    public Lender(String nm, String phno, double br, char interest)
    {
        name = nm;
        base_rate = br;
        contact = phno;
        id = (int)(Math.random() * (100000 - 10000 + 1) + 10000); // 4-digit ids assigned
        interest_type = interest;
        address = "NA"; // not given
        borrowers = new ArrayList<Integer>();
        moneyleft = new HashMap<Integer, Double>();
    }

    public Lender(String nm, String phno, double br, char interest, String addr)
    {
        name = nm;
        base_rate = br;
        contact = phno;
        id = (int)(Math.random() * (100000 - 10000 + 1) + 10000); // 4-digit ids assigned
        interest_type = interest;
        address = addr;
        borrowers = new ArrayList<Integer>();
        moneyleft = new HashMap<Integer, Double>();
    }

    public void lend(int borrower_id, double amt)
    {
        borrowers.add(borrower_id);
        moneyleft.put(borrower_id, amt);
    }

    public void get_installment_amt(int borrower_id, int installment_no) 
    {
        // depending on the amount left to repay and the interest rate required for the borrower, return the installment amt
    }

    public void paid_installment(int borrower_id, int installment_no)
    {
        // update the amount left to repay
    }

    public ArrayList<Integer> get_borrowers()
    {
        return borrowers;
    }

    public double rem_amt(int bid)
    {
        return moneyleft.get(bid);
    }
}

class Borrower
{
    String name, contact, address;
    int id, credit_score;
    ArrayList<Integer> lenders; // people who he owes money
    public Borrower(String nm, String phno, String addr)
    {
        name = nm;
        contact = phno;
        address = addr;
        id = (int)(Math.random() * (100000 - 10000 + 1) + 10000);
        credit_score = 0; // initially at the start 
        lenders = new ArrayList<Integer>();
    }
    
    public void add_lender(int lid)
    {
        lenders.add(lid);
    }
    
    public ArrayList<Integer> get_lenders()
    {
        return lenders;
    }
}

public class LoaningService
{
    static HashMap<Integer, Lender> lenders;
    static HashMap<Integer, Borrower> borrowers;

    public static void main(String []args)
    {
        lenders = new HashMap<Integer, Lender>();
        borrowers = new HashMap<Integer, Borrower>();

        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to Loaning Servce...");

        restart:
        while(true)
        {
            System.out.println("New User? y/n");
            String utype = sc.nextLine();
            if(utype.equals("y")) 
            {
                while(true)
                {
                    System.out.println("Lender(hit l) or Borrower(hit b):");
                    String lorb = sc.nextLine();
                    if(lorb.equals("l"))
                    {
                        System.out.println("Enter Name:");
                        String name = sc.nextLine();
                        System.out.println("Enter Contact Number:");
                        String phno = sc.nextLine();
                        System.out.println("Enter Addresss:");
                        String addr = sc.nextLine();
                        System.out.println("Enter Base Rate that you take from Borrowers:");
                        double rate = Double.parseDouble(sc.nextLine());
                        System.out.println("Enter Interest Type that you take ('S' for Simple, 'C' for Compound):");
                        char c = sc.nextLine().charAt(0);

                        Lender l = new Lender(name, phno, rate, c, addr);
                        lenders.put(l.id, l); 

                        System.out.println("Lender added successfully!\n Your id is " + l.id+". Keep it safe for future use");
                        continue restart;
                    }
                    else if(lorb.equals("b"))
                    {
                        System.out.println("Enter Name:");
                        String name = sc.nextLine();
                        System.out.println("Enter Contact Number:");
                        String phno = sc.nextLine();
                        System.out.println("Enter Addresss:");
                        String addr = sc.nextLine();

                        Borrower b = new Borrower(name, phno, addr);
                        borrowers.put(b.id, b);

                        System.out.println("Borrower added successfully!\n Your id is "+ b.id+". Keep it safe for future use.");
                        continue restart;
                    }
                    else 
                    {
                        System.out.println("Enter l or b only\n");
                        continue;
                    }
                }
            }
            else if(utype.equals("n")) 
            {
                while(true)
                {
                    System.out.println("Lender(hit l) or Borrower(hit b)\n");
                    String lorb = sc.nextLine();
                    if(lorb.equals("l"))
                    {
                        System.out.println("Welcome Lender\n");
                        while(true)
                        {
                            System.out.println("Enter id:\n");
                            int id = Integer.parseInt(sc.nextLine());
                            if(lenders.containsKey(id))
                            {
                                Lender currLender = lenders.get(id);
                                System.out.println("Hi there!" + currLender.name);
                                System.out.println("What do you want to do? Type \n 1. list: to list your borrowers info \n 2. exit: to leave");
                                while(true)
                                {
                                    String option = sc.nextLine();
                                    switch(option)
                                    {
                                        case "list": ArrayList<Integer> bids = currLender.get_borrowers();
                                            System.out.println("Name\t Contact\t Amount Left");
                                            for(int bid: bids)
                                            {
                                                Borrower b = borrowers.get(bid);
                                                System.out.println(b.name+"\t"+b.contact+"\t"+currLender.rem_amt(bid));
                                            }
                                            break;
                                        case "exit":
                                            continue restart;
                                    }
                                }
                            }
                            else
                            {
                                System.out.println("Wrong ID! Retry.");
                                continue;
                            }       
                        }
                    }
                    else if(lorb.equals("b"))
                    {
                        System.out.println("Welcome Borrower\n");
                        while(true)
                        {
                            System.out.println("Enter id:\n");
                            int id = Integer.parseInt(sc.nextLine());
                            if(borrowers.containsKey(id))
                            {
                                Borrower currBorrower = borrowers.get(id);
                                System.out.println("Hi there!" + currBorrower.name);
                                System.out.println("What do you want to do? Type \n 1. list: to list your lenders info \n 2. lenders: list of available lenders \n 3. borrow: borrow money \n 4. exit: to leave");
                                while(true)
                                {
                                    String option = sc.nextLine();
                                    switch(option)
                                    {
                                        case "list": ArrayList<Integer> lids = currBorrower.get_lenders();
                                            System.out.println("Name\t Contact\t Amount Left");
                                            for(int lid: lids)
                                            {
                                                Lender l = lenders.get(lid);
                                                System.out.println(l.name+"\t"+l.contact+"\t"+l.rem_amt(id));
                                            }
                                            break;
                                        case "lenders": 
                                            System.out.println("Lender ID\t Interest Rate(%)\t Interest Type(S/C)");
                                            for (Map.Entry<Integer, Lender> e : lenders.entrySet())
                                            {
                                                Lender l = e.getValue();
                                                System.out.println(e.getKey() + "\t" + l.base_rate + "\t" + l.interest_type);

                                            }
                                            break;
                                        case "borrow":
                                            System.out.println("Enter Lender Id:");
                                            int lid = Integer.parseInt(sc.nextLine());
                                            System.out.println("Enter Amount Required:");
                                            double amt = Double.parseDouble(sc.nextLine());
                                            if(lenders.containsKey(lid))
                                            {
                                                Lender l = lenders.get(lid);
                                                l.lend(id, amt);
                                                borrowers.get(id).add_lender(lid);
                                                
                                                System.out.println("Borrowing Successfull!!");
                                            }
                                            else
                                            {
                                                System.out.println("No such lender!!");
                                            }
                                            break;
                                        case "exit":
                                            continue restart;
                                    }
                                }
                            }
                            else
                            {
                                System.out.println("Wrong ID! Retry.");
                                continue;
                            }       
                        }
                    }
                    else
                    {
                        System.out.println("Enter l or b only\n");
                        continue;
                    }
                }
            }
            else
            {
                System.out.println("Enter y or n only\n");
                continue;
            }
        }
    }
}
