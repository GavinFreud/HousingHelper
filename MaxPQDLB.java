/*
DLB to store MaxPQs, ripped off from my first project for 1501 with minor alterations (and lack of the "findFullFromPrefix" method)
*/


public class MaxPQDLB{

	private MaxPQDLBNode root= new MaxPQDLBNode('\u0000');

	public void insert(String addr, AptMaxPQ max){
        if(addr == null){
			System.out.println("Please print a valid address");
			return;
		}
		char[] addrArray = convertToCA(addr);
		MaxPQDLBNode current = root;

		for(int x=0; x<addrArray.length; x++){
            boolean endOfString = (x == addrArray.length-1);
            if(current.getData() == '\u0000'){
            	current.setData(addrArray[x]);
            	current.setChild(new MaxPQDLBNode('\u0000'));
            	if(endOfString){
            		current.setPQ(max);
            	}
            	current = current.getChild();
            } else {
            	MaxPQDLBNode iterNode = current;
            	while(iterNode != null){
            		if(iterNode.getData()==addrArray[x]){
            			if(iterNode.getChild() == null){
            				iterNode.setChild(new MaxPQDLBNode('\u0000'));
            			}
            			if(endOfString){
            				iterNode.setPQ(max);
            			}
            			current = iterNode.getChild();
            			break;
            		} else{
            			if(iterNode.getSibling() == null){
            				iterNode.setSibling(new MaxPQDLBNode(addrArray[x]));
            				if(endOfString){
            					iterNode.getSibling().setPQ(max);
            				}
            				iterNode.getSibling().setChild(new MaxPQDLBNode('\u0000'));
            				current = iterNode.getSibling().getChild();
            			} else {
            				iterNode = iterNode.getSibling();
            			}
            		}

            		
            	}
            }

        }
	}

	public AptMaxPQ getSpacePQ(String s){
        MaxPQDLBNode current = root;
        char[] stringChar = convertToCA(s);
        int depth = 0;
        while(current != null){
            if(current.getData() == stringChar[depth]){
                if(depth == stringChar.length-1){
                    return current.getPQ();
                }
                current = current.getChild();
                depth++;
            } else {
                current = current.getSibling();
            }
        } return null;
    }

	private char[] convertToCA(String s){

        char[] addrArray = new char[s.length()];
        for(int x=0; x<s.length(); x++){
            if(s.charAt(x) == '\''){
                addrArray[x]='\'';
            }
            addrArray[x] = s.charAt(x);
        }
        return addrArray;
    }

    class MaxPQDLBNode{
    private MaxPQDLBNode child;
    private MaxPQDLBNode sibling;
    private char data;
    private AptMaxPQ pq;

    public MaxPQDLBNode(char value){
        data = value;
    }

    public MaxPQDLBNode getChild(){
        return child;
    }

    public MaxPQDLBNode getSibling(){
        return sibling;
    }

    public char getData(){
        return data;
    }

    public AptMaxPQ getPQ(){
        return pq;
    }



    public void setChild(MaxPQDLBNode c){
        child = c;
    }

    public void setSibling(MaxPQDLBNode s){
        sibling = s;
    }

    public void setData(char value){
        data= value;
    }

    public void setPQ(AptMaxPQ maxq){
        pq = maxq;
    }



}

}

