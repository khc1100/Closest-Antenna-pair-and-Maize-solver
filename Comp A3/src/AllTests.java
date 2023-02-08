import org.junit.jupiter.api.*;

import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class AllTests {
    private Point2D point1;
    private Point2D point2;
    private Point2D point3;
    private Point2D point4;
    private Point2D point5;
    private Point2D point6;
    private Point2D point7;
    private Point2D point8;
    private Point2D point9;
    private Point2D point10;
    private Point2D point11;
    private Point2D point12;
    private Point2D point13;
    private Point2D point14;
    private Point2D point15;

    char[][] m0,m1,m2,m3,m4,m5,m6,m7,m8,m9,m10,m11,m12,m13,m14,m15,m16;
    Maze solver;

    @BeforeEach
    void setUp() throws IOException {
        point1 = new Point2D(0.9058,0.1270);
        point2 = new Point2D(0.9134, 0.6324);
        point3 = new Point2D(0.0975, 0.2785);
        point4 = new Point2D(0.5469, 0.9575);
        point5 = new Point2D(0.9649, 0.1576);
        point6 = new Point2D(0.9706, 0.9572);
        point7 = new Point2D(0.4854, 0.8003);
        point8 = new Point2D(0.1419, 0.4218);
        point9 = new Point2D(0.9157, 0.7922);
        point10 = new Point2D(0.9595, 0.6557);
        point11 = new Point2D(0.0357, 0.8491);
        point12 = new Point2D(0.9340, 0.6787);
        point13 = new Point2D(0.7577, 0.7431);
        point14 = new Point2D(0.3922, 0.6555);
        point15 = new Point2D(0.1712, 0.7060);

        solver = new Maze();

        m0 = solver.loadMaze("mazes/m0.txt");
        m1 = solver.loadMaze("mazes/m1.txt");
        m2 = solver.loadMaze("mazes/m2.txt");
        m3 = solver.loadMaze("mazes/m3.txt");
        m4 = solver.loadMaze("mazes/m4.txt");
        m5 = solver.loadMaze("mazes/m5.txt");
        m6 = solver.loadMaze("mazes/m6.txt");
        m7 = solver.loadMaze("mazes/m7.txt");
        m8 = solver.loadMaze("mazes/m8.txt");
        m9 = solver.loadMaze("mazes/m9.txt");
        m10 = solver.loadMaze("mazes/m10.txt");
        m11 = solver.loadMaze("mazes/m11.txt");
        m12 = solver.loadMaze("mazes/m12.txt");
        m13 = solver.loadMaze("mazes/m13.txt");
        m14 = solver.loadMaze("mazes/m14.txt");
        m15 = solver.loadMaze("mazes/m15.txt");
        m16 = solver.loadMaze("mazes/m16.txt");
    }

    public static char[][] deepCopy(char[][] original) {
        if (original == null) {
            return null;
        }


        final char[][] result = new char[original.length][];
        for (int i = 0; i < original.length; i++) {
            result[i] = Arrays.copyOf(original[i], original[i].length);
        }
        return result;
    }

    // base case: empty pointsA
    @Test
    @Tag("score:2")
    @DisplayName("Base case test 1")
    void baseCaseTest1() {
        Assertions.assertTimeoutPreemptively(Duration.ofSeconds(5), () -> {
            Point2D p1 = new Point2D(-0.2, 0.4);
            Point2D p2 = new Point2D(0.15, -0.1);
            Point2D p3 = new Point2D(-0.06, 0.15);
            Point2D[] pointsA = {p1, p2, p3};
            Point2D[] pointsB = {};
            ClosestAntennaPair closestAntennaPair = new ClosestAntennaPair(pointsA, pointsB);
            assertEquals(Double.POSITIVE_INFINITY, closestAntennaPair.distance());
        });
    }

    // base case: empty pointsB
    @Test
    @Tag("score:2")
    @DisplayName("Base case test 2")
    void baseCaseTest2() {
        Assertions.assertTimeoutPreemptively(Duration.ofSeconds(5), () -> {
            Point2D p1 = new Point2D(-0.2, 0.4);
            Point2D p2 = new Point2D(0.15, -0.1);
            Point2D p3 = new Point2D(-0.06, 0.15);
            Point2D[] pointsA = {};
            Point2D[] pointsB = {p1, p2, p3};
            ClosestAntennaPair closestAntennaPair = new ClosestAntennaPair(pointsA, pointsB);
            assertEquals(Double.POSITIVE_INFINITY, closestAntennaPair.distance());
        });
    }

    // base case: one point in pointsA
    @Test
    @Tag("score:3")
    @DisplayName("Base case test 3")
    void baseCaseTest3() {
        Assertions.assertTimeoutPreemptively(Duration.ofSeconds(5), () -> {
            Point2D p1 = new Point2D(-0.2, 0.4);
            Point2D p2 = new Point2D(0.15, -0.1);
            Point2D p3 = new Point2D(-0.06, 0.15);
            Point2D[] pointsA = {p2};
            Point2D[] pointsB = {p1,p3};
            ClosestAntennaPair closestAntennaPair = new ClosestAntennaPair(pointsA, pointsB);
            assertEquals(p2.distanceTo(p3), closestAntennaPair.distance());
        });
    }

    // base case: one point in pointsB
    @Test
    @Tag("score:3")
    @DisplayName("Base case test 4")
    void baseCaseTest4() {
        Assertions.assertTimeoutPreemptively(Duration.ofSeconds(5), () -> {
            Point2D p1 = new Point2D(-0.2, 0.4);
            Point2D p2 = new Point2D(0.15, -0.1);
            Point2D p3 = new Point2D(-0.06, 0.15);
            Point2D[] pointsA = {p1,p3};
            Point2D[] pointsB = {p2};
            ClosestAntennaPair closestAntennaPair = new ClosestAntennaPair(pointsA, pointsB);
            assertEquals(p2.distanceTo(p3), closestAntennaPair.distance());
        });
    }

    // recursive case: aPoints on the right of bPoints
    @Test
    @Tag("score:3")
    @DisplayName("Recursive case 1")
    void recursiveTest1() {
        Assertions.assertTimeoutPreemptively(Duration.ofSeconds(5), () -> {
            Point2D p1 = new Point2D(0.1,1);
            Point2D p2 = new Point2D(-0.1,1);
            Point2D p3 = new Point2D(0.2,2);
            Point2D p4 = new Point2D(-0.2,2);
            Point2D p5 = new Point2D(0.3,3);
            Point2D p6 = new Point2D(-0.3,3);
            Point2D[] aPoints = {p1, p3, p5};
            Point2D[] bPoints = {p2, p4, p6};

            ClosestAntennaPair pair = new ClosestAntennaPair(aPoints, bPoints);
            assertEquals(0.2, pair.distance());
            assertTrue(pair.getCounter() > 1);
        });
    }

    // recursive case: aPoints on the left of bPoints
    @Test
    @Tag("score:3")
    @DisplayName("Recursive case 2")
    void recursiveTest2() {
        Assertions.assertTimeoutPreemptively(Duration.ofSeconds(5), () -> {
            Point2D p1 = new Point2D(0.1,1);
            Point2D p2 = new Point2D(-0.1,1);
            Point2D p3 = new Point2D(0.2,2);
            Point2D p4 = new Point2D(-0.2,2);
            Point2D p5 = new Point2D(0.3,3);
            Point2D p6 = new Point2D(-0.3,3);
            Point2D[] aPoints = {p2, p4, p6};
            Point2D[] bPoints = {p1, p3, p5};

            ClosestAntennaPair pair = new ClosestAntennaPair(aPoints, bPoints);
            assertEquals(0.2, pair.distance());
            assertTrue(pair.getCounter() > 1);
        });
    }

    // recursive case: aPoints below bPoints
    @Test
    @Tag("score:2")
    @DisplayName("Recursive case 3")
    void recursiveTest3() {
        Assertions.assertTimeoutPreemptively(Duration.ofSeconds(5), () -> {
            Point2D p1 = new Point2D(1,0.1);
            Point2D p2 = new Point2D(1,-0.1);
            Point2D p3 = new Point2D(2,0.2);
            Point2D p4 = new Point2D(2,-0.2);
            Point2D p5 = new Point2D(3,0.3);
            Point2D p6 = new Point2D(3,-0.3);
            Point2D[] aPoints = {p2, p4, p6};
            Point2D[] bPoints = {p1, p3, p5};


            ClosestAntennaPair pair = new ClosestAntennaPair(aPoints, bPoints);
            assertEquals(0.2, pair.distance());
        });
    }

    // recursive case: aPoints above bPoints
    @Test
    @Tag("score:2")
    @DisplayName("Recursive case 4")
    void recursiveTest4() {
        Assertions.assertTimeoutPreemptively(Duration.ofSeconds(5), () -> {
            Point2D p1 = new Point2D(1,0.1);
            Point2D p2 = new Point2D(1,-0.1);
            Point2D p3 = new Point2D(2,0.2);
            Point2D p4 = new Point2D(2,-0.2);
            Point2D p5 = new Point2D(3,0.3);
            Point2D p6 = new Point2D(3,-0.3);
            Point2D[] aPoints = {p1, p3, p5};
            Point2D[] bPoints = {p2, p4, p6};


            ClosestAntennaPair pair = new ClosestAntennaPair(aPoints, bPoints);
            assertEquals(0.2, pair.distance());
            assertTrue(pair.getCounter() > 1);
        });
    }

    // recursive case: aPoints and bPoints are well mixed
    @Test
    @Tag("score:3")
    @DisplayName("Recursive case 5")
    void recursiveTest5() {
        Assertions.assertTimeoutPreemptively(Duration.ofSeconds(5), () -> {
            Point2D p1 = new Point2D(-0.2, 0.4);
            Point2D p2 = new Point2D(0.15, -0.1);
            Point2D p3 = new Point2D(-0.06, 0.15);
            Point2D p4 = new Point2D(0.3, 0.5);
            Point2D p5 = new Point2D(-0.15, 0.25);
            Point2D p6 = new Point2D(0.15, 0.3);
            Point2D p7 = new Point2D(0.35, -0.1);

            Point2D[] aPoints = {p1, p3, p4, p7};
            Point2D[] bPoints = {p2, p5, p6};

            ClosestAntennaPair closestAntennaPair = new ClosestAntennaPair(aPoints, bPoints);
            assertEquals(p3.distanceTo(p5), closestAntennaPair.distance());
            assertTrue(closestAntennaPair.getCounter() > 1);
        });
    }

    // Test with random points. Feel free to use this as a template to make your own tests!
    @Test
    @Tag("score:2")
    @DisplayName("Recursive case 6")
    void recursiveTest6() {
        Assertions.assertTimeoutPreemptively(Duration.ofSeconds(5), () -> {
            // The test may take too long and potentially lead to a timeout error if n > 10000
            int n = 100;
            // Each point in A will take value in the box [aXMin, aXMax) x [aYMin, aYMax)
            // and each point in B will be in the box [bXMin, bXMax) x [bYMin, bYMax).
            // Feel free to modify the values to change the size and location of the boxes.
            // However, if your box is invalid, this test will fail with a RunTimeException.
            double aXMin = -1;
            double aXMax = 1;
            double aYMin = 1;
            double aYMax = 2;
            double bXMin = 1;
            double bXMax = 2;
            double bYMin = 0;
            double bYMax = 4;

            if (aXMax < aXMin || aYMax < aYMin || bXMax < bXMin || bYMax < bYMin) {
                throw new RuntimeException("Box constraint is not valid");
            }

            Point2D[] aPoints = new Point2D[n];
            Point2D[] bPoints = new Point2D[n];
            for (int i = 0; i < n; i++) {
                double aX = aXMin + Math.random() * (aXMax - aXMin);
                double aY = aYMin + Math.random() * (aYMax - aYMin);
                double bX = bXMin + Math.random() * (bXMax - bXMin);
                double bY = bYMin + Math.random() * (bYMax - bYMin);
                aPoints[i] = new Point2D(aX, aY);
                bPoints[i] = new Point2D(bX, bY);
            }
            ClosestAntennaPair closestAntennaPair = new ClosestAntennaPair(aPoints, bPoints);
            ClosestAntennaPairBruteForce closestAntennaPairBruteForce = new ClosestAntennaPairBruteForce(aPoints, bPoints);
            assertEquals(closestAntennaPairBruteForce.getClosestDistance(), closestAntennaPair.distance());
        });
    }

    @Test
    @Tag("private")
    @Tag("score:3")
    @DisplayName("Recursive case test 7")
    void recursiveTest7() {
        Assertions.assertTimeoutPreemptively(Duration.ofSeconds(5), () -> {
            Point2D[] aPoints = {point1, point3, point5, point8, point10};
            Point2D[] bPoints = {point4, point7, point9, point11};

            ClosestAntennaPair closestAntennaPair = new ClosestAntennaPair(aPoints, bPoints);
            assertEquals(point10.distanceTo(point9), closestAntennaPair.distance());
        });

    }

    @Test
    @Tag("private")
    @Tag("score:3")
    @DisplayName("Recursive case test 8")
    void recursiveTest8() {
        Assertions.assertTimeoutPreemptively(Duration.ofSeconds(5), () -> {
            Point2D[] aPoints = {point3, point4, point7, point8, point11};
            Point2D[] bPoints = {point1, point2, point5, point6, point10};

            ClosestAntennaPair closestAntennaPair = new ClosestAntennaPair(aPoints, bPoints);
            assertEquals(point4.distanceTo(point6), closestAntennaPair.distance());
        });
    }

    @Test
    @Tag("private")
    @Tag("score:3")
    @DisplayName("Recursive case test 9")
    void recursiveTest9() {
        Assertions.assertTimeoutPreemptively(Duration.ofSeconds(5), () -> {
            Point2D[] aPoints = {point1, point2, point3, point4, point5, point6, point7, point8, point9, point10, point11, point12};
            Point2D[] bPoints = {point13, point14, point15};

            ClosestAntennaPair closestAntennaPair = new ClosestAntennaPair(aPoints, bPoints);
            assertEquals(point13.distanceTo(point9), closestAntennaPair.distance());
        });
    }

    @Test
    @Tag("private")
    @Tag("score:3")
    @DisplayName("Recursive case test 10")
    void recursiveTest10() {
        Assertions.assertTimeoutPreemptively(Duration.ofSeconds(5), () -> {
            Point2D[] aPoints = {point1, point2, point3, point4, point5, point6, point7};
            Point2D[] bPoints = {point8, point9, point10, point11, point12, point13, point14};

            ClosestAntennaPair closestAntennaPair = new ClosestAntennaPair(aPoints, bPoints);
            assertEquals(point2.distanceTo(point12), closestAntennaPair.distance());
        });
    }

    @Test
    @Tag("private")
    @Tag("score:3")
    @DisplayName("closest sorting test 1")
    void closestSortingTest1() {
        Assertions.assertTimeoutPreemptively(Duration.ofSeconds(5), () -> {
            Point2D[] aPointsSortedByX = {point3, point4, point1, point2, point5};
            Point2D[] bPointsSortedByX = {point6};

            Point2D[] aPointsSortedByY = {point3, point4, point1, point2, point5};
            Point2D[] bPointsSortedByY = {point6};

            List<Point2D> aPointsSortedByYExpected = Arrays.asList(point1, point5, point3, point2, point4);

            Point2D[] auxA = new Point2D[5];
            Point2D[] auxB = new Point2D[1];

            ClosestAntennaPair closestAntennaPair = new ClosestAntennaPair(new Point2D[0], new Point2D[0]);
            closestAntennaPair.closest(aPointsSortedByX,  bPointsSortedByX, aPointsSortedByY, bPointsSortedByY, auxA, auxB, 0, 0, 4, 0);
            assertEquals(aPointsSortedByYExpected, Arrays.asList(aPointsSortedByY));
        });
    }

    @Test
    @Tag("private")
    @Tag("score:4")
    @DisplayName("closest sorting test 2")
    void closestSortingTest2() {
        Assertions.assertTimeoutPreemptively(Duration.ofSeconds(5), () -> {
            Point2D[] aPointsSortedByX = {point3, point4, point1, point2, point5};
            Point2D[] bPointsSortedByX = {point8, point7, point9, point10, point6};

            Point2D[] aPointsSortedByY = {point3, point4, point1, point2, point5};
            Point2D[] bPointsSortedByY = {point8, point7, point9, point10, point6};

            List<Point2D> aPointsSortedByYExpected = Arrays.asList(point1, point5, point3, point2, point4);
            List<Point2D> bPointsSortedByYExpected = Arrays.asList(point8, point10, point9, point7, point6);

            Point2D[] auxA = new Point2D[5];
            Point2D[] auxB = new Point2D[5];

            ClosestAntennaPair closestAntennaPair = new ClosestAntennaPair(new Point2D[0], new Point2D[0]);
            closestAntennaPair.closest(aPointsSortedByX,  bPointsSortedByX, aPointsSortedByY, bPointsSortedByY, auxA, auxB, 0, 0, 4, 4);
            assertEquals(aPointsSortedByYExpected, Arrays.asList(aPointsSortedByY));
            assertEquals(bPointsSortedByYExpected, Arrays.asList(bPointsSortedByY));
        });
    }

    @Test
    @Tag("private")
    @Tag("score:3")
    @DisplayName("recursion check 1")
    void recursionCheckTest1() {
        Assertions.assertTimeoutPreemptively(Duration.ofSeconds(5), () -> {
            Point2D[] aPoints = {point1, point2, point3, point4, point5};
            Point2D[] bPoints = {point6, point7, point8, point9, point10};

            ClosestAntennaPair closestAntennaPair = new ClosestAntennaPair(aPoints, bPoints);
            assertTrue(closestAntennaPair.getCounter() >= 3);
        });

    }

    @Test
    @Tag("private")
    @Tag("score:3")
    @DisplayName("recursion check 2")
    void recursionCheckTest2() {
        Assertions.assertTimeoutPreemptively(Duration.ofSeconds(5), () -> {
            Point2D[] aPoints = {point1, point2, point3, point4, point5, point6, point7};
            Point2D[] bPoints = {point8, point9, point10, point11, point12, point13, point14, point15};

            ClosestAntennaPair closestAntennaPair = new ClosestAntennaPair(aPoints, bPoints);
            assertTrue(closestAntennaPair.getCounter() >= 5);
        });
    }


    @Test
    @Tag("score:6")
    @DisplayName("Maze 0")
    void mazeTest0() {
        Assertions.assertTimeoutPreemptively(Duration.ofSeconds(3), () -> {
            assertTrue(solver.solveMaze(m0));
        });
    }

    @Test
    @Tag("score:7")
    @DisplayName("Maze 1")
    void mazeTest1() {

        Assertions.assertTimeoutPreemptively(Duration.ofSeconds(3), () -> {
            assertTrue(solver.solveMaze(m1));
        });
    }

    @Test
    @Tag("score:6")
    @DisplayName("Maze 2")
    void mazeTest2() {

        Assertions.assertTimeoutPreemptively(Duration.ofSeconds(3), () -> {
            assertTrue(solver.solveMaze(m2));
        });
    }

    @Test
    @Tag("score:6")
    @DisplayName("Maze 3")
    void mazeTest3() {
        Assertions.assertTimeoutPreemptively(Duration.ofSeconds(3), () -> {
            assertFalse(solver.solveMaze(m3));
        });
    }

    @Test
    @Tag("private")
    @Tag("score:1")
    @DisplayName("Maze 0b")
    void mazeTest0b() {
        Assertions.assertTimeoutPreemptively(Duration.ofSeconds(3), () -> {
            assertTrue(solver.solveMaze(m0));
            assertTrue(solver.counter > 10);
            assertTrue(new Aux().validMazeSolve(m0));

        });
    }

    @Test
    @Tag("private")
    @Tag("score:1")
    @DisplayName("Maze 1b")
    void mazeTest1b() {
        Assertions.assertTimeoutPreemptively(Duration.ofSeconds(3), () -> {
            assertTrue(solver.solveMaze(m1));
            assertTrue(solver.counter > 10);
            assertTrue(new Aux().validMazeSolve(m1));
        });

    }

    @Test
    @Tag("private")
    @Tag("score:1")
    @DisplayName("Maze 2b")
    void mazeTest2b() {
        Assertions.assertTimeoutPreemptively(Duration.ofSeconds(3), () -> {
            assertTrue(solver.solveMaze(m2));
            assertTrue(solver.counter > 10);
            assertTrue(new Aux().validMazeSolve(m2));
        });
    }

    @Test
    @Tag("private")
    @Tag("score:1")
    @DisplayName("Maze 3b")
    void mazeTest3b() {
        Assertions.assertTimeoutPreemptively(Duration.ofSeconds(3), () -> {
            char [][] expected = deepCopy(m3);
            assertFalse(solver.solveMaze(m3));
            assertTrue(Arrays.deepEquals(expected, m3));
            assertTrue(solver.counter > 10);
        });
    }

    @Test
    @Tag("private")
    @Tag("score:1")
    @DisplayName("Maze 4")
    void mazeTest4() {
        Assertions.assertTimeoutPreemptively(Duration.ofSeconds(3), () -> {
            assertTrue(solver.solveMaze(m4));
            assertTrue(solver.counter > 10);
            assertTrue(new Aux().validMazeSolve(m4));
        });
    }

    @Test
    @Tag("private")
    @Tag("score:1")
    @DisplayName("Maze 5")
    void mazeTest5() {
        Assertions.assertTimeoutPreemptively(Duration.ofSeconds(3), () -> {
            assertTrue(solver.solveMaze(m5));
            assertTrue(solver.counter > 10);
            assertTrue(new Aux().validMazeSolve(m5));
        });
    }

    @Test
    @Tag("private")
    @Tag("score:1")
    @DisplayName("Maze 6")
    void mazeTest6() {
        Assertions.assertTimeoutPreemptively(Duration.ofSeconds(3), () -> {
            assertTrue(solver.solveMaze(m6));
            assertTrue(solver.counter > 10);
            assertTrue(new Aux().validMazeSolve(m6));
        });
    }

    @Test
    @Tag("private")
    @Tag("score:1")
    @DisplayName("Maze 7")
    void mazeTest7() {
        Assertions.assertTimeoutPreemptively(Duration.ofSeconds(3), () -> {
            assertTrue(solver.solveMaze(m7));
            assertTrue(solver.counter > 10);
            assertTrue(new Aux().validMazeSolve(m7));
        });
    }

    @Test
    @Tag("private")
    @Tag("score:1")
    @DisplayName("Maze 8")
    void mazeTest8() {
        Assertions.assertTimeoutPreemptively(Duration.ofSeconds(3), () -> {
            assertTrue(solver.solveMaze(m8));
            assertTrue(solver.counter > 10);
            assertTrue(new Aux().validMazeSolve(m8));
        });
    }

    @Test
    @Tag("private")
    @Tag("score:2")
    @DisplayName("Maze 9")
    void mazeTest9() {
        Assertions.assertTimeoutPreemptively(Duration.ofSeconds(3), () -> {
            assertTrue(solver.solveMaze(m9));
            assertTrue(solver.counter > 10);
            assertTrue(new Aux().validMazeSolve(m9));
        });
    }


    //No key
    @Test
    @Tag("private")
    @Tag("score:2")
    @DisplayName("Maze 10")
    void mazeTest10() {
        Assertions.assertTimeoutPreemptively(Duration.ofSeconds(3), () -> {
            char [][] expected = deepCopy(m10);
            assertFalse(solver.solveMaze(m10));
            assertTrue(Arrays.deepEquals(expected, m10));
            assertTrue(solver.counter > 10);
        });
    }

    //Loop unsolvable
    @Test
    @Tag("private")
    @Tag("score:2")
    @DisplayName("Maze 11")
    void mazeTest11() {
        Assertions.assertTimeoutPreemptively(Duration.ofSeconds(3), () -> {
            char [][] expected = deepCopy(m11);
            assertFalse(solver.solveMaze(m11));
            assertTrue(Arrays.deepEquals(expected, m11));
            assertTrue(solver.counter > 10);
        });
    }

    @Test
    @Tag("private")
    @Tag("score:2")
    @DisplayName("Maze 12")
    void mazeTest12() {
        Assertions.assertTimeoutPreemptively(Duration.ofSeconds(3), () -> {
            assertTrue(solver.solveMaze(m12));
            assertTrue(solver.counter > 10);
            assertTrue(new Aux().validMazeSolve(m12));        });
    }

    @Test
    @Tag("private")
    @Tag("score:2")
    @DisplayName("Maze 13")
    void mazeTest13() {
        Assertions.assertTimeoutPreemptively(Duration.ofSeconds(3), () -> {
            assertTrue(solver.solveMaze(m13));
            assertTrue(solver.counter > 10);
            assertTrue(new Aux().validMazeSolve(m13));});
    }

    @Test
    @Tag("private")
    @Tag("score:2")
    @DisplayName("Maze 14")
    void mazeTest14() {
        Assertions.assertTimeoutPreemptively(Duration.ofSeconds(3), () -> {
            char [][] expected = deepCopy(m14);
            assertFalse(solver.solveMaze(m14));
            assertTrue(Arrays.deepEquals(expected, m14));
            assertTrue(solver.counter > 10);
        });
    }

    @Test
    @Tag("private")
    @Tag("score:2")
    @DisplayName("Maze 15")
    void mazeTest15() {
        Assertions.assertTimeoutPreemptively(Duration.ofSeconds(3), () -> {
            assertTrue(solver.solveMaze(m15));
            assertTrue(solver.counter > 10);
            assertTrue(new Aux().validMazeSolve(m15));});
    }

    @Test
    @Tag("private")
    @Tag("score:2")
    @DisplayName("Maze 16")
    void mazeTest16() {
        Assertions.assertTimeoutPreemptively(Duration.ofSeconds(3), () -> {
            assertTrue(solver.solveMaze(m16));
            assertTrue(solver.counter > 10);
            assertTrue(new Aux().validMazeSolve(m16));});
    }
}
