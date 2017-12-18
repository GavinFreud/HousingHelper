/*
DLB to store MinPQs, ripped off from my first project for 1501 with minor alterations (and lack of the "findFullFromPrefix" method)
*/


public class MinPQDLB{

    private MinPQDLBNode root= new MinPQDLBNode('\u0000');

    public void insert(String addr, AptMinPQ Min){
        if(addr == null){
            System.out.println("Please print a valid address");
            return;
        }
        char[] addrArray = convertToCA(addr);
        MinPQDLBNode current = root;

        for(int x=0; x<addrArray.length; x++){
            boolean endOfString = (x == addrArray.length-1);
            if(current.getData() == '\u0000'){
                current.setData(addrArray[x]);
                current.setChild(new MinPQDLBNode('\u0000'));
                if(endOfString){
                    current.setPQ(Min);
                }
                current = current.getChild();
            } else {
                MinPQDLBNode iterNode = current;
                while(iterNode != null){
                    if(iterNode.getData()==addrArray[x]){
                        if(iterNode.getChild() == null){
                            iterNode.setChild(new MinPQDLBNode('\u0000'));
                        }
                        if(endOfString){
                            iterNode.setPQ(Min);
                        }
                        current = iterNode.getChild();
                        break;
                    } else{
                        if(iterNode.getSibling() == null){
                            iterNode.setSibling(new MinPQDLBNode(addrArray[x]));
                            if(endOfString){
                                iterNode.getSibling().setPQ(Min);
                            }
                            iterNode.getSibling().setChild(new MinPQDLBNode('\u0000'));
                            current = iterNode.getSibling().getChild();
                        } else {
                            iterNode = iterNode.getSibling();
                        }
                    }

                    
                }
            }

        }
    }

    public AptMinPQ getPricePQ(String s){
        MinPQDLBNode current = root;
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

    class MinPQDLBNode{
    private MinPQDLBNode child;
    private MinPQDLBNode sibling;
    private char data;
    private AptMinPQ pq;

    public MinPQDLBNode(char value){
        data = value;
    }

    public MinPQDLBNode getChild(){
        return child;
    }

    public MinPQDLBNode getSibling(){
        return sibling;
    }

    public char getData(){
        return data;
    }

    public AptMinPQ getPQ(){
        return pq;
    }



    public void setChild(MinPQDLBNode c){
        child = c;
    }

    public void setSibling(MinPQDLBNode s){
        sibling = s;
    }

    public void setData(char value){
        data= value;
    }

    public void setPQ(AptMinPQ Minq){
        pq = Minq;
    }



}

}

