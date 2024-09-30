import java.util.*;
import java.io.*;

public class USACO2023JanuaryBronze2 {
    static StringTokenizer st;
    static BufferedReader br;
    static PrintWriter out;

    static String next() throws IOException {
        while (st == null || !st.hasMoreTokens())
            st = new StringTokenizer(br.readLine().trim());
        return st.nextToken();
    }

    static int nextInt() throws IOException {
        return Integer.parseInt(next());
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
//        br = new BufferedReader(new FileReader("pipe.in"));
//        out = new PrintWriter(new BufferedWriter(new FileWriter("pipe.out")));
        int num = nextInt();
        String l = next()+"0";

        ArrayList<Integer> group = new ArrayList<>();

        int gstart = -1;
        int curr;
        int prev = 0;
        for(int i=0; i<l.length(); i++){
            curr=l.charAt(i)-'0';
            if(curr-prev==1){
                gstart=i;
            }
            if(curr-prev==-1){
                group.add(i-gstart);
            }
            prev=curr;
        }

        int maxDays = Integer.MAX_VALUE;

        int start = 0;
        int end = group.size();
        if(l.charAt(0)=='1'){
            start++;
            maxDays=group.get(0)-1;
        }
        if(l.charAt(num-1)=='1'){
            end--;
            maxDays=Math.min(maxDays,group.get(end)-1);
        }
        for(int i=start; i<end; i++){
            maxDays = Math.min(maxDays,(group.get(i)-1)/2);
        }
        int diameter = maxDays*2+1;

        int sum = 0;
        for(int i:group){
            sum+=(i-1)/diameter+1;
        }
        out.println(sum);
        out.close();
    }
}
