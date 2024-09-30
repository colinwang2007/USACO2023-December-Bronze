
import java.util.*;
import java.io.*;

public class USACO2023JanuaryBronze {
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

        int c = nextInt();
        int cc = nextInt();
        long[] cow = new long[c];

        ArrayList<Integer> index = new ArrayList<>();
        long curmax =0;
        for(int i=0; i<c; i++) {
            cow[i] = nextInt();
            if (cow[i] >curmax){
                index.add(i);
                curmax = cow[i];
            }
        }
        for(int i=0; i<cc; i++){
            int candy = nextInt();
            long ate = 0;
            for(int idx : index){
                long h = cow[idx];
                if(candy<=h){
                    cow[idx]+=candy-ate;
                    break;
                }
                cow[idx]+=h-ate;
                ate=h;
            }
            curmax = 0;
            ArrayList<Integer> nextIndex = new ArrayList<>();
            for(int currIndex : index){
                if(cow[currIndex]>curmax){
                    curmax=cow[currIndex];
                    nextIndex.add(currIndex);
                }
            }
            index=nextIndex;
        }
        for(long i:cow){
            out.println(i);
        }
        out.close();
    }
}
