package hw2;

import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;


public class PercolationStats {

    public int[] total_open;
    public double con_low, con_high;

    // perform T independent experiments on an N-by-N grid
    public PercolationStats(int N, int T, PercolationFactory pf){
        total_open = new int[T];
        for(int i = 0; i < T; i +=1) {
            Percolation pc = pf.make(N);
            while (!pc.percolates()) {
                int row = StdRandom.uniform(N);
                int col = StdRandom.uniform(N);
                if (!pc.isOpen(row, col)) {
                    pc.open(row, col);
                }
            }
            total_open[i] =  pc.numberOfOpenSites();
        }
        con_high = mean() + (1.96 * stddev())/(Math.sqrt(T));
        con_low = mean() - (1.96 * stddev())/(Math.sqrt(T));
    }

    // sample mean of percolation threshold
    public double mean(){
        return StdStats.mean(total_open);
    }

    // sample standard deviation of percolation threshold
    public double stddev(){
        return StdStats.stddev(total_open);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLow(){
        return con_low;
    }

    // high endpoint of 95% confidence interval
    public double confidenceHigh(){
        return con_high;
    }
}















