import java.util.*;
import java.io.*;

public class USACO2023JanuaryBronze3 {
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
        int cases = nextInt();
        for(int ii=0; ii<cases; ii++){

            // set order
            int plants = nextInt();
            int[][] arr = new int[plants][3];
            for(int i=0; i<plants; i++) arr[i][0]=nextInt();
            for(int i=0; i<plants; i++)arr[i][1]=nextInt();
            for(int i=0; i<plants; i++)arr[i][2]=nextInt();

            long[][] rate = new long[plants][2];
            for(int i=0; i<plants; i++){
                int index = arr[i][2];
                rate[index][0]=arr[i][0];
                rate[index][1]=arr[i][1];
            }

            //compute
            out.println(compute(plants,rate));

        }
        out.close();
    }
    static long compute(int plants, long[][] rate){
        long min = 0;
        long max = Integer.MAX_VALUE;
        //rate[0] is the initial size, rate[1] is the rate of growth
        for(int i=0; i<plants-1; i++){ //compare each with the next one
            //current and next sizes and growth rates
            long currSize = rate[i][0]; //i
            long currRate = rate[i][1];
            long nextSize = rate[i+1][0];//i+1
            long nextRate = rate[i+1][1];

            if(currSize>nextSize){
                if(currRate<nextRate){//smaller rate - there is a point next will catch up
//                    int diff = currSize-nextSize;
//                    int diffRate = nextRate-currRate;
//                    int m = diff/diffRate;
//                    max=Math.min(max,m);
                    max=Math.min(max,(long)Math.ceil((double)((currSize-nextSize)/(nextRate-currRate))));
                }
                //if currRate>=nextRate, ignore
            }
            if(currSize<=nextSize){
                if(currRate<=nextRate){
                    return -1;
                }
                else{
//                    int diff =nextSize-currSize;
//                    int diffRate = currRate-nextRate;
//                    int m = diff/diffRate+1;
//                    min=Math.max(min,m);
                    min=Math.max(min,(nextSize-currSize)/(currRate-nextRate)+1);
                }
            }
            //impossible case
            if(min>max){
                return -1;
            }
        }
        return min;
    }
}
/*
6
1
10
1
0
2
7 3
8 10
1 0
2
3 6
10 8
0 1
2
7 3
8 9
1 0
2
7 7
8 8
0 1
2
7 3
8 8
1 0

 */
/*
2
5
7 4 1 10 12
3 4 5 2 1
2 1 0 3 4
5
4 10 12 7 1
3 1 1 4 5
2 4 3 1 0

 */
