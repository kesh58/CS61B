package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    WeightedQuickUnionUF DS;
    public int side_length;
    public int num_open;

    /* Create N-by-N grid, with all sites initial*/
    public Percolation(int N){
        if(N >0){
            DS = new WeightedQuickUnionUF(N*N*2 + 2);
            side_length = N;
        } else{
            throw new IllegalArgumentException("N must be greater than 0!");
        }
    }

    /* Open the site (row, col) if it is not open*/
    public void open(int row, int col){
        if(row > side_length - 1 || col > side_length -1 || row < 0 || col < 0){
            throw new IndexOutOfBoundsException("row and col must less than side length - 1");
        } else if (!isOpen(row, col)) {
            DS.union(row * side_length + col, row * side_length + col + side_length * side_length);
            num_open += 1;
            if (col + 1 < side_length && isOpen(row, col + 1)) {
                DS.union(row * side_length + col, row * side_length + col + 1);
            }
            if (col - 1 >= 0 && isOpen(row, col - 1)) {
                DS.union(row * side_length + col, row * side_length + col - 1);
            }
            if (row + 1 < side_length && isOpen(row + 1, col)) {
                DS.union(row * side_length + col, (row + 1) * side_length + col);
            }
            if (row - 1 >= 0 && isOpen(row - 1, col)) {
                DS.union(row * side_length + col, (row - 1) * side_length + col);
            }
            if (row == 0) {
                DS.union(row * side_length + col, side_length * side_length * 2);
            } else if (row == side_length - 1) {
                DS.union(row * side_length + col, side_length * side_length * 2 + 1);
            }
        }
    }

    /* Is the site (row, col) open?*/
    public boolean isOpen(int row, int col){
        if(row > side_length - 1 || col > side_length -1 || row < 0 || col < 0){
            throw new IndexOutOfBoundsException("row and col must less than side length - 1");
        } else {
            return DS.find(row * side_length + col) == DS.find(row * side_length + col+ side_length * side_length);
        }
    }

    /* Is the site (row, col) full?*/
    public boolean isFull(int row, int col){
        return DS.find(row* side_length + col) == DS.find(side_length * side_length * 2);
    }

    /* Number of open sites*/
    public int numberOfOpenSites(){
        return num_open;
    }

    /* Does the system percolate?*/
    public boolean percolates(){
        return DS.find(side_length * side_length * 2) == DS.find(side_length * side_length * 2 +1);
    }

    /* Used for testing. */
    public static void main(String[] args){
        Percolation DS = new Percolation(5);
        DS.open(2, 3);
        DS.open(2, 3);
        DS.open(2, 4);
        DS.open(4, 2);
        DS.open(0, 0);
        System.out.println(DS.isOpen(2, 3));
        System.out.println(DS.isOpen(3, 3));
        System.out.println(DS.isOpen(3, 2));
        System.out.println(DS.isOpen(0, 0));
        System.out.println(DS.numberOfOpenSites());
        DS.open(3, 2);
        DS.open(3, 3);
        DS.open(1, 4);
        DS.open(0, 4);
        DS.open(4, 0);
        System.out.println(DS.isFull(4,2));
        System.out.println(DS.isFull(4,0));
        System.out.println(DS.percolates());
    }
}























