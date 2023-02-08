import java.util.Arrays;

public class ClosestAntennaPair {

    private double closestDistance = Double.POSITIVE_INFINITY;
    private long counter = 0;

    public ClosestAntennaPair(Point2D[] aPoints, Point2D[] bPoints) {
        // Insert your solution here.
        // Making sure there proper inputs
        int n = aPoints.length;
        if(n<1) return;
        int b = bPoints.length;
        if(b<1) return;

        //Sort aPoints by X ordered
        Point2D[] aPointsSortedByX = new Point2D[n];
        for (int i=0; i<n; i++){
            aPointsSortedByX[i] = aPoints[i];
        }
        Arrays.sort(aPointsSortedByX,Point2D.Y_ORDER);
        Arrays.sort(aPointsSortedByX,Point2D.X_ORDER);

        //Sort bPoints by X ordered
        Point2D[] bPointsSortedByX = new Point2D[b];
        for (int i= 0; i < b; i++){
            bPointsSortedByX[i] = bPoints[i];
        }
        Arrays.sort(bPointsSortedByX,Point2D.Y_ORDER);
        Arrays.sort(bPointsSortedByX,Point2D.X_ORDER);


        //CHECK FOR CO-INCIDENT POINTS STILL NEED TO DOkk

        // set up points for The Y sorted Arrays
        Point2D[] aPointsSortedByY = new Point2D[n];
        for(int i=0; i<n; i++){
            aPointsSortedByY[i] = aPointsSortedByX[i];
        }

        Point2D[] bPointsSortedByY = new Point2D[b];
        for(int i=0; i<b; i++){
            bPointsSortedByY[i] = bPointsSortedByX[i];
        }

        // Auxillary arrays
        Point2D[] auxA = new Point2D[n];
        Point2D[] auxB = new Point2D[b];

        closest (aPointsSortedByX, bPointsSortedByX, aPointsSortedByY, bPointsSortedByY, auxA, auxB, 0, 0, n-1, b-1);

    }

    public double closest(Point2D[] aPointsSortedByX, Point2D[] bPointsSortedByX, Point2D[] aPointsSortedByY, Point2D[] bPointsSortedByY, Point2D[] auxA, Point2D[] auxB, int lowA, int lowB, int highA, int highB) {
        // please do not delete/modify the next line!
        counter++;
        //WRITE THE CODE HERE WAAAAAAAA
        //***BASE CASE IMPORTANT
        System.out.println(counter);
        if(highA<lowA) return Double.POSITIVE_INFINITY;
        if(highB<lowB) return Double.POSITIVE_INFINITY;

        //if there is only 1 point in A
        if (highA == lowA){
            for (int i=lowB; i <= highB; i++){

                double distance = aPointsSortedByY[0].distanceTo(bPointsSortedByX[i]);
                if(distance<closestDistance)
                    closestDistance=distance;
            }
            return closestDistance;

        }
        if (highB == lowB) {
            for (int i = lowA; i <= highA; i++){
                double distance = bPointsSortedByX[0].distanceTo(aPointsSortedByX[i]);
                if(distance<closestDistance)
                    closestDistance = distance;

            }

            return closestDistance;

        }


        //Find MEDIAN
        int midA = (lowA + (highA-lowA) / 2);
        int midB = (lowB+(highB-lowB)/2);
        Point2D median = bPointsSortedByX[midB];
       //Point2D medianA = aPointsSortedByX[midA];
        //Point2D median = aPointsSortedByX[midA];
       // Point2D medianB = bPointsSortedByX[midB];

        //DOUBLE CHECK DIS PLEASE
        double delta1 = closest(aPointsSortedByX, bPointsSortedByX, aPointsSortedByY, bPointsSortedByY, auxA, auxB, lowA, lowB, midA, midB);
        double delta2 = closest(aPointsSortedByX, bPointsSortedByX, aPointsSortedByY, bPointsSortedByY, auxA, auxB, midA+1, midB+1, highA, highB);


       double delta = Math.min(delta1,delta2);


        merge(aPointsSortedByY, auxA, lowA, midA, highA);
        merge(bPointsSortedByY, auxB, lowB, midB, highB);
        //BASICALLY COPIED FROM CLOSESTPAIR

        //checking all the points within |delta| from the median
        int m = 0;
        for(int i = lowA; i<= highA; i++){
            if(aPointsSortedByY[i].x()- median.x() < delta) {
                auxA[m++] = aPointsSortedByY[i];
            }
        }

        int p =0;
        for(int i = lowB;i<=highB;i++){
            if(bPointsSortedByY[i].x() - median.x() < delta) {
                auxB[p++] = bPointsSortedByY[i];
            }
        }

        for (int i = 0; i < m; i++){

            for(int j = 0; (j < p) ;j++){

                double distance = auxA[i].distanceTo(auxB[j]);
                if(distance < delta){
                    delta = distance;

                    if(distance < closestDistance)
                        closestDistance = delta;
                }
            }
        }
        // Insert your solution here and modify the return statement.
        closestDistance = delta;
        return delta;
    }

    public double distance() {
        return closestDistance;
    }

    public long getCounter() {
        return counter;
    }

    // stably merge a[low .. mid] with a[mid+1 ..high] using aux[low .. high]
    // precondition: a[low .. mid] and a[mid+1 .. high] are sorted subarrays, namely sorted by y coordinate
    // this is the same as in ClosestPair
    private static void merge(Point2D[] a, Point2D[] aux, int low, int mid, int high) {
        // copy to aux[]
        // note this wipes out any values that were previously in aux in the [low,high] range we're currently using

        for (int k = low; k <= high; k++) {
            aux[k] = a[k];
        }

        int i = low, j = mid + 1;
        for (int k = low; k <= high; k++) {
            if (i > mid) a[k] = aux[j++];   // already finished with the low list ?  then dump the rest of high list
            else if (j > high) a[k] = aux[i++];   // already finished with the high list ?  then dump the rest of low list
            else if (aux[i].compareByY(aux[j]) < 0)
                a[k] = aux[i++]; // aux[i] should be in front of aux[j] ? position and increment the pointer
            else a[k] = aux[j++];
        }
    }
}
