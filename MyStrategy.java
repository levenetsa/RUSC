import model.*;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.CopyOnWriteArrayList;

public final class MyStrategy implements Strategy {
    double turn;
    double eng;
    Queue<Point> q;
    int d[][];
    int dPx, dPy;
    int s;
    int s1;
    boolean nitro;
    double ph;
    boolean print = false;
    Point way[];
    int ind;
    boolean brackes;
    int backDrive = 0, fag = 0;
    int start;
    boolean flag =false;

    private void wtd(TileType tileType, TileType whish, Car self, World world, Game game, Move move, Point[] way) {
        double spx = self.getSpeedX();
        double spy = self.getSpeedY();
        double Seng = 1;
        double Teng = 0.8;
        if (whish != null)
            tileType = whish;
        if (print) System.out.println("\nspeed " + (int) (spx * 100) + " " + (int) (100 * spy));
        //System.out.println(" co kak " + self.getX() / 800 + " " + self.getX() / 800 + " " + tileType);
        double p = 0.2;
        double dD = 150D;
        // System.out.println(tileType);
        if (tileType.compareTo(TileType.HORIZONTAL) == 0) {
            eng = Seng;
            if (self.getWheelTurn() > 0.5 && self.getX() % 800 < 200 && self.getX() % 800 > 600) {
                turn = 1;
                nitro = true;
            } else {
                turn = 0;
            }//self.getWheelTurn()/16;

        }
        if (tileType.compareTo(TileType.VERTICAL) == 0) {
            eng = Seng;
            if (self.getWheelTurn() > 0.5 && Math.abs(self.getY()) % 800 < 200 && Math.abs(self.getY()) % 800 > 600) {
                turn = 1;
            } else {
                turn = 0;//self.getWheelTurn()/4;
                // nitro = true;
            }
        }
        if (tileType.compareTo(TileType.LEFT_TOP_CORNER) == 0) {
            eng = Teng;
            turn = 1D;
            if (spx < -1) {
                turn = -1D;
            }
         /*   if () {
                move.setBrake(true);
                move.setUseNitro(true);
            }*/

        }

        if (tileType.compareTo(TileType.BOTTOM_HEADED_T) == 0) {
            if (ind > 1) {
                if (ph < Math.PI / 4 && ph > -Math.PI / 4) {
                    double corner = atan2((int) way[ind - 1].y / 800 - (int) way[ind - 2].y / 800, (int) way[ind - 1].x / 800 - (int) way[ind - 2].x / 800);
                    if (corner == -Math.PI / 2) {
                        wtd(tileType, TileType.RIGHT_TOP_CORNER, self, world, game, move, way);
                        return;
                    }
                    if (corner == 0) {
                        wtd(tileType, TileType.HORIZONTAL, self, world, game, move, way);
                        return;
                    }
                }
                if (ph > Math.PI / 4 && ph < Math.PI / 4 * 3) {
                    double corner = atan2((int) way[ind - 1].y / 800 - (int) way[ind - 2].y / 800, (int) way[ind - 1].x / 800 - (int) way[ind - 2].x / 800);
                    if (corner == -Math.PI) {
                        wtd(tileType, TileType.RIGHT_TOP_CORNER, self, world, game, move, way);
                        return;
                    }
                    if (corner == 0) {
                        wtd(tileType, TileType.LEFT_TOP_CORNER, self, world, game, move, way);
                        return;
                    }
                }
                if (ph > Math.PI / 4 * 3 && ph < -Math.PI / 4 * 3) {
                    double corner = atan2((int) way[ind - 1].y / 800 - (int) way[ind - 2].y / 800, (int) way[ind - 1].x / 800 - (int) way[ind - 2].x / 800);
                    if (corner == -Math.PI) {
                        wtd(tileType, TileType.HORIZONTAL, self, world, game, move, way);
                        return;
                    }
                    if (corner == -Math.PI / 2) {
                        wtd(tileType, TileType.LEFT_TOP_CORNER, self, world, game, move, way);
                        return;
                    }
                }
            }
        }

        if (tileType.compareTo(TileType.LEFT_HEADED_T) == 0) {
            if (ind > 1) {
                if (ph < Math.PI / 4 && ph > -Math.PI / 4) {
                    double corner = atan2((int) way[ind - 1].y / 800 - (int) way[ind - 2].y / 800, (int) way[ind - 1].x / 800 - (int) way[ind - 2].x / 800);
                    if (corner == -Math.PI / 2) {
                        wtd(tileType, TileType.RIGHT_TOP_CORNER, self, world, game, move, way);
                        return;
                    }
                    if (corner == Math.PI / 2) {
                        wtd(tileType, TileType.RIGHT_BOTTOM_CORNER, self, world, game, move, way);
                        return;
                    }
                }
                if (ph > Math.PI / 4 && ph < Math.PI / 4 * 3) {
                    double corner = atan2((int) way[ind - 1].y / 800 - (int) way[ind - 2].y / 800, (int) way[ind - 1].x / 800 - (int) way[ind - 2].x / 800);
                    if (corner == Math.PI) {
                        wtd(tileType, TileType.RIGHT_TOP_CORNER, self, world, game, move, way);
                        return;
                    }
                    if (corner == Math.PI / 2) {
                        wtd(tileType, TileType.VERTICAL, self, world, game, move, way);
                        return;
                    }
                }
                if (ph > -Math.PI / 4 * 3 && ph < -Math.PI / 4) {
                    double corner = atan2((int) way[ind - 1].y / 800 - (int) way[ind - 2].y / 800, (int) way[ind - 1].x / 800 - (int) way[ind - 2].x / 800);
                    if (corner == Math.PI) {
                        wtd(tileType, TileType.RIGHT_BOTTOM_CORNER, self, world, game, move, way);
                        return;
                    }
                    if (corner == -Math.PI / 2) {
                        wtd(tileType, TileType.VERTICAL, self, world, game, move, way);
                        return;
                    }
                }
            }
        }

        if (tileType.compareTo(TileType.RIGHT_HEADED_T) == 0) {
            if (ind > 1) {
                //  System.out.println("HERE " + (Math.atan2(way[ind - 3].y - way[ind - 2].y, way[ind - 3].x - way[ind - 2].y) + Math.PI / 2));

                if (Math.PI / 4 * 3 < ph || ph < -Math.PI / 4 * 3) {
                    double corner = atan2((int) way[ind - 1].y / 800 - (int) way[ind - 2].y / 800, (int) way[ind - 1].x / 800 - (int) way[ind - 2].x / 800);
                    //  System.out.println("x " + (int) way[ind - 2].x/800 + " " + (int)way[ind - 1].x/800);
                    //   System.out.println("y " + (int) way[ind - 2].y/800 + " " + (int)way[ind - 1].y/800);
                    //   System.out.println("zaexali sprava" + corner);
                    //     for (int i = 0; i < ind; i++)
                    //       System.out.print((int) way[i].x / 800 + " ");
                    //  System.out.println();
                    // for (int i = 0; i < ind; i++)
                    //   System.out.print((int) way[i].y / 800 + " ");
                    // System.out.println();
                    if (corner == -Math.PI / 2) {
                        //    System.out.println("nalevo");
                        wtd(tileType, TileType.LEFT_TOP_CORNER, self, world, game, move, way);
                        return;
                    }
                    if (corner == Math.PI / 2) {
                        //       System.out.println("napravo");
                        wtd(tileType, TileType.LEFT_BOTTOM_CORNER, self, world, game, move, way);
                        return;
                    }
                }
                if (Math.PI / 4 < ph && ph < Math.PI / 4 * 3) {
                    // System.out.println("zaexali snizy");
                    double corner = atan2((int) way[ind - 1].y / 800 - (int) way[ind - 2].y / 800, (int) way[ind - 1].x / 800 - (int) way[ind - 2].x / 800);
                    if (corner == 0) {
                        wtd(tileType, TileType.LEFT_TOP_CORNER, self, world, game, move, way);
                        return;
                    }
                    if (corner == Math.PI / 2) {
                        wtd(tileType, TileType.VERTICAL, self, world, game, move, way);
                        return;
                    }
                }
                if (-Math.PI / 4 < ph && ph > -Math.PI / 4 * 3) {
                    //    System.out.println("zaexali sverhy");
                    double corner = atan2((int) way[ind - 1].y / 800 - (int) way[ind - 2].y / 800, (int) way[ind - 1].x / 800 - (int) way[ind - 2].x / 800);
                    if (corner == 0) {
                        wtd(tileType, TileType.LEFT_BOTTOM_CORNER, self, world, game, move, way);
                        return;
                    }
                    if (corner == -Math.PI / 2) {
                        wtd(tileType, TileType.VERTICAL, self, world, game, move, way);
                        return;
                    }
                }
            }
        }

        if (tileType.compareTo(TileType.TOP_HEADED_T) == 0) {
            if (ind > 1) {
                if (ph < Math.PI / 4 && ph > -Math.PI / 4) {
                    double corner = atan2((int) way[ind - 1].y / 800 - (int) way[ind - 2].y / 800, (int) way[ind - 1].x / 800 - (int) way[ind - 2].x / 800);
                    if (corner == Math.PI / 2) {
                        wtd(tileType, TileType.RIGHT_BOTTOM_CORNER, self, world, game, move, way);
                        return;
                    }
                    if (corner == 0) {
                        wtd(tileType, TileType.HORIZONTAL, self, world, game, move, way);
                        return;
                    }
                }
                if (ph < -Math.PI / 4 && ph > -Math.PI / 4 * 3) {
                    double corner = atan2((int) way[ind - 1].y / 800 - (int) way[ind - 2].y / 800, (int) way[ind - 1].x / 800 - (int) way[ind - 2].x / 800);
                    if (corner == Math.PI) {
                        wtd(tileType, TileType.RIGHT_TOP_CORNER, self, world, game, move, way);
                        return;
                    }
                    if (corner == 0) {
                        wtd(tileType, TileType.LEFT_TOP_CORNER, self, world, game, move, way);
                        return;
                    }
                }
                if (ph < -Math.PI / 4 * 3 || ph > Math.PI / 4 * 3) {
                    //      System.out.println("заехали справа");
                    double corner = atan2((int) way[ind - 1].y / 800 - (int) way[ind - 2].y / 800, (int) way[ind - 1].x / 800 - (int) way[ind - 2].x / 800);
                    if (corner == Math.PI / 2) {
                        //      System.out.println("вверх");
                        wtd(tileType, TileType.LEFT_TOP_CORNER, self, world, game, move, way);
                        return;
                    }
                    //   System.out.println("за " + corner);
                    if (corner == Math.PI) {
                        //        System.out.println("налево");
                        wtd(tileType, TileType.HORIZONTAL, self, world, game, move, way);
                        return;
                    }
                }
            }
        }

        if (tileType.compareTo(TileType.CROSSROADS) == 0) {

        }

        if (tileType.compareTo(TileType.RIGHT_TOP_CORNER) == 0) {
            eng = Teng;
            turn = 1D;
            if (spy < -2) turn = -1D;
            //if (self.getWidth() % 1 < dD && self.getWidth() % 1 > dD * p)
            //  move.setBrake(true);
        }
        if (tileType.compareTo(TileType.RIGHT_BOTTOM_CORNER) == 0) {
            eng = Teng;
            turn = 1D;
            if (spx > 2) turn = -1D;
            // if (self.getHeight() % 1 < dD && self.getHeight() % 1 > dD * p)
            //    move.setBrake(true);
        }
        if (tileType.compareTo(TileType.LEFT_BOTTOM_CORNER) == 0) {
            eng = Teng;
            turn = 1D;
            if (spy > 2) turn = -1D;
            // if (self.getWidth() % 1 < dD && self.getHeight() % 1 >dD * p)
            //     move.setBrake(true);
        }
    }

    private double atan2(int y, int x) {
        x = -x;
        //     System.out.println("x " + x + " y " + y);
        if (x == 0)
            if (y > 0) {
                return Math.PI / 2;
            } else {
                return -Math.PI / 2;
            }
        return Math.atan2(y, x);
    }

    public double corrSp(TileType tileType, Car self, World world, Game game, Move move) {
        double dTurn = 0;
        double sX = self.getSpeedX();
        double sY = self.getSpeedY();
        double c = game.getCarAngularSpeedFactor();
        if (tileType.compareTo(TileType.HORIZONTAL) == 0) {
            if (sX > 0) {
                if (sY > 0) dTurn = -0.1D;
                if (sY < 0) dTurn = 0.1D;
            } else {
                if (sY < 0) dTurn = -0.1D;
                if (sY > 0) dTurn = 0.1D;
            }
        }
        if (tileType.compareTo(TileType.VERTICAL) == 0) {
            if (sY > 0) {
                if (sX > 0.01) dTurn = 0.1D;
                if (sX < 0.01) dTurn = -0.1D;
            } else {
                if (sX < 0.01) dTurn = 0.1D;
                if (sX > 0.01) dTurn = -0.1D;
            }
        }
        return dTurn;
    }

    private class Point {
        double x;
        double y;

        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    public Point doGraph(TileType tiles[][], Car self) {
        double x = self.getX();
        double y = self.getY();

        if (print)
            System.out.println("\nnew step begining we are at : (" + (int) x / 800 + ", " + (int) y / 800 + ")\n");
        d[((int) (x / 800))][((int) (y / 800))] = 1;
        q = new LinkedList<Point>();
        q.add(new Point(x, y));
        //      System.out.println(" where " + ((int) (x / 800)) + " " + ((int) (y / 800)));
        //     System.out.println("TileType " + tiles[((int) (x / 800))][((int) (y / 800))]);
        //      System.out.println(" dX " + dPx + " " + dPy);
        return bfs(tiles);
    }

    private Point bfs(TileType tiles[][]) {
        Point c = q.peek();
        q.remove();
        if (print)
            System.out.print("current " + (int) c.x / 800 + " " + (int) c.y / 800 + " " + (int) c.x + " " + (int) c.y);
        if ((int) c.x / 800 == dPx && (int) c.y / 800 == dPy) {
            if (print) System.out.println(" done");
            return c;
        } else {
            if (print) System.out.println(" false " + dPx + " " + dPy);
        }

        int curr = d[((int) (c.x / 800))][((int) (c.y / 800))];
        double yR, xR;
        int i, j;
        i = 0;
        j = 1;
        xR = (c.x + 800 * (i - 1));
        yR = (c.y + 800 * (j - 1));
        if (print) System.out.println("  check " + xR / 800 + " " + yR / 800 + " " + xR + " " + yR);
        if (xR >= 0 && xR < s * 800 && yR >= 0 && yR < s1 * 800 &&
                ifDrivable(tiles[((int) c.x / 800)][((int) c.y / 800)], tiles[((int) (xR / 800))][((int) (yR / 800))], i, j)) {
            if (print) System.out.println("  passed");
            if (d[((int) (xR / 800))][((int) (yR / 800))] == 0 || curr + 1 < d[((int) (xR / 800))][((int) (yR / 800))]) {
                q.add(new Point(xR, yR));
                if (print) System.out.println("  added");
                d[((int) (xR / 800))][((int) (yR / 800))] = curr + 1;
            }
        }
        i = 1;
        j = 0;
        xR = (int) (c.x + 800 * (i - 1));
        yR = (int) (c.y + 800 * (j - 1));
        if (print) System.out.println("  check " + xR / 800 + " " + yR / 800 + " " + xR + " " + yR);
        if (xR >= 0 && xR < s * 800 && yR >= 0 && yR < s1 * 800 &&
                ifDrivable(tiles[((int) c.x / 800)][((int) c.y / 800)], tiles[((int) (xR / 800))][((int) (yR / 800))], i, j)) {
            if (print) System.out.println("  passed");
            if (d[((int) (xR / 800))][((int) (yR / 800))] == 0) {//|| curr + 1 < d[xR  / 800][yR  / 800]
                q.add(new Point(xR, yR));
                if (print) System.out.println("  added");
                d[((int) (xR / 800))][((int) (yR / 800))] = curr + 1;
            }
        }
        i = 1;
        j = 2;
        xR = (int) (c.x + 800 * (i - 1));
        yR = (int) (c.y + 800 * (j - 1));
        if (print) System.out.println("  check " + xR / 800 + " " + yR / 800 + " " + xR + " " + yR);
        if (xR >= 0 && xR < s * 800 && yR >= 0 && yR < s1 * 800 &&
                ifDrivable(tiles[((int) c.x / 800)][((int) c.y / 800)], tiles[((int) (xR / 800))][((int) (yR / 800))], i, j)) {
            if (print) System.out.println("  passed");
            if (d[((int) (xR / 800))][((int) (yR / 800))] == 0 || curr + 1 < d[((int) (xR / 800))][((int) (yR / 800))]) {
                q.add(new Point(xR, yR));
                if (print) System.out.println("  added");
                d[((int) (xR / 800))][((int) (yR / 800))] = curr + 1;
            }
        }
        i = 2;
        j = 1;
        xR = (int) (c.x + 800 * (i - 1));
        yR = (int) (c.y + 800 * (j - 1));
        if (print) System.out.println("  check " + xR / 800 + " " + yR / 800 + " " + xR + " " + yR);
        if (xR >= 0 && xR < s * 800 && yR >= 0 && yR < s1 * 800 &&
                ifDrivable(tiles[((int) c.x / 800)][((int) c.y / 800)], tiles[((int) (xR / 800))][((int) (yR / 800))], i, j)) {
            if (print) System.out.println("  passed");
            if (d[((int) (xR / 800))][((int) (yR / 800))] == 0 || curr + 1 < d[((int) (xR / 800))][((int) (yR / 800))]) {
                q.add(new Point(xR, yR));
                if (print) System.out.println("  added");
                d[((int) (xR / 800))][((int) (yR / 800))] = curr + 1;
            }
        }
        return bfs(tiles);
    }


    private boolean ifDrivable(TileType c, TileType tileType, int i, int j) {
        if (c.compareTo(TileType.EMPTY) == 0)
            return true;
        if (c.compareTo(TileType.HORIZONTAL) == 0) {
            if (i !=1) return true;
        }
        if (c.compareTo(TileType.VERTICAL) == 0) {
            if (j != 1) return true;
        }
        if (c.compareTo(TileType.LEFT_TOP_CORNER) == 0) {
            if ((i == 2 && j == 1)||(i==1 && j == 2)) return true;
        }

        if (c.compareTo(TileType.BOTTOM_HEADED_T) == 0) {
            if (i == 1 && j == 0) return false; else return true;
        }

        if (c.compareTo(TileType.LEFT_HEADED_T) == 0) {
            if (i == 2 && j == 1) return false; else return true;
        }

        if (c.compareTo(TileType.RIGHT_HEADED_T) == 0) {
            if (i == 0 && j == 1) return false; else return true;
        }

        if (c.compareTo(TileType.TOP_HEADED_T) == 0) {
            if (i == 1 && j == 2) return false; else return true;
        }

        if (c.compareTo(TileType.CROSSROADS) == 0) {
            return true;
        }

        if (c.compareTo(TileType.RIGHT_TOP_CORNER) == 0) {
            if ((i == 0 && j == 1)||(i==1 && j == 2)) return true;
        }
        if (c.compareTo(TileType.RIGHT_BOTTOM_CORNER) == 0) {
            if ((i == 1 && j == 0)||(i==0 && j == 1)) return true;
        }
        if (c.compareTo(TileType.LEFT_BOTTOM_CORNER) == 0) {
            if ((i == 1 && j == 0)||(i==2 && j == 1)) return true;
        }
        return false;
    }

    Collection<TileType> HORIZONTAL;
    Collection<TileType> VERTICAL;
    Collection<TileType> LEFT_TOP_CORNER;
    Collection<TileType> BOTTOM_HEADED_T;
    Collection<TileType> LEFT_HEADED_T;
    Collection<TileType> RIGHT_HEADED_T;
    Collection<TileType> TOP_HEADED_T;
    Collection<TileType> CROSSROADS;
    Collection<TileType> RIGHT_TOP_CORNER;
    Collection<TileType> RIGHT_BOTTOM_CORNER;
    Collection<TileType> LEFT_BOTTOM_CORNER;



    private Point getTracks(Point c) {


        int x = (int) (c.x / 800);
        int y = (int) (c.y / 800);
        if (d[x][y] == 2) {
            way[ind] = new Point(c.x - 800 + 800, c.y);
            ind++;
            return c;
        }
        if (x > 1 && d[x][y] - 1 == d[x - 1][y]) {
            way[ind] = new Point(c.x - 800 + 800, c.y);
            ind++;
            return getTracks(new Point(c.x - 800, c.y));
        }
        if (y > 1 && d[x][y] - 1 == d[x][y - 1]) {
            way[ind] = new Point(c.x - 800 + 800, c.y);
            ind++;
            return getTracks(new Point(c.x, c.y - 800));
        }
        if (y < s1 - 1 && d[x][y] - 1 == d[x][y + 1]) {
            way[ind] = new Point(c.x - 800 + 800, c.y);
            ind++;
            return getTracks(new Point(c.x, c.y + 800));
        }
        if (x < s - 1 && d[x][y] - 1 == d[x + 1][y]) {
            way[ind] = new Point(c.x - 800 + 800, c.y);
            ind++;
            return getTracks(new Point(c.x + 800, c.y));
        }
        return null;

    }

    private double getAlnge(Car self, TileType tiles[][], int[][] wayP) {
        //if (Math.sqrt(self.getSpeedX() * self.getSpeedX() + self.getSpeedY() * self.getSpeedY()) < 1)
        //    return self.getAngleTo(way[ind - 1].x, (int) way[ind - 1].y);
        if (ind > 1) {
            return self.getAngleTo(way[ind - 2].x, (int) way[ind - 2].y);
        } else {
            int olddPx = dPx;
            int olddPy = dPy;
            int i;
            for (i = 0; i < wayP.length; i++)
                if (wayP[i][0] == dPx && wayP[i][1] == dPy) break;
            while (ind < 2) {
                i++;
                i = i % (wayP.length);
                dPx = wayP[i][0];
                dPy = wayP[i][1];
                initBfsMass(s, s1);
                Point dest = doGraph(tiles, self);
                way = new Point[200];
                ind = 0;
                getTracks(dest);
            }
            dPx = olddPx;
            dPy = olddPy;
            return self.getAngleTo(way[ind - 2].x, (int) way[ind - 2].y);
        }
    }

    private double corDirr(TileType tileType, Car self) {
        double dTurn = 0;
        double carW = self.getWidth();
        double pX = self.getX();
        double pY = self.getY();
        double sX = self.getSpeedX();
        double sY = self.getSpeedY();
        ;
        if (tileType.compareTo(TileType.HORIZONTAL) == 0) {
            if (sX > 0) {
                if (pY % 800 < 200 + carW / 2) {
                    dTurn = 0.2D;
                }
                if (pY % 800 > 700 - carW / 2) {
                    dTurn = -0.2D;
                }
            } else {
                if (pY % 800 < 100 + carW / 2) {
                    dTurn = -0.2D;
                }
                if (pY % 800 > 600 - carW / 2) {
                    dTurn = 0.2D;
                }
            }
        }
        if (tileType.compareTo(TileType.VERTICAL) == 0) {
            if (sY < 0) {
                if (pX % 800 < 200 + carW / 2) {
                    dTurn = 0.2D;
                }
                if (pX % 800 > 700 - carW / 2) {
                    dTurn = -0.2D;
                }
            } else {
                if (pX % 800 < 100 + carW / 2) {
                    dTurn = -0.2D;
                }
                if (pX % 800 > 600 - carW / 2) {
                    dTurn = 0.2D;
                }
            }
        }
        return dTurn;
    }

    @Override
    public void move(Car self, World world, Game game, Move move) {
        Car [] pl = world.getCars();
        int counter = 0;
        for (int i = 0; i < pl.length; i++)
            if (pl[i].isTeammate()) counter++;
        if (counter == 2) {
            move.setEnginePower(2);
            return;
        }
        if (!flag)
            start = game.getInitialFreezeDurationTicks() + 150;
        flag = true;
        if (start>0) start--;
        brackes = false;
        if (backDrive > 0) backDrive--;
        if (fag > 0) fag--;
        if (collapse(self, world) && fag == 0) {
            move.setEnginePower(-0.5);
        }
        double maxSp = 1;
        double x = self.getNextWaypointX();
        double y = self.getNextWaypointY();
        nitro = false;
        dPx = (int) x;
        dPy = (int) y;
        double dS;
        TileType tiles[][] = world.getTilesXY();
        int[][] wayP = world.getWaypoints();
        s = game.getWorldWidth();
        s1 = game.getWorldHeight();
        initBfsMass(s, s1);
        Point dest = doGraph(tiles, self);
        way = new Point[200];
        ind = 0;
        getTracks(dest);
        double ms = getAlnge(self, tiles, wayP);
        if (ind >= 1 && start == 0)
            move.setWheelTurn(ms + corDirr(tiles[(int) way[ind - 1].x / 800][(int) way[ind - 1].y / 800], self));
        else move.setWheelTurn(ms);
        if (Math.abs(ms)>Math.PI/5 && Math.sqrt(self.getSpeedX() * self.getSpeedX() + self.getSpeedY() * self.getSpeedY()) > 16) move.setBrake(true);
        System.out.println(Math.sqrt(self.getSpeedX() * self.getSpeedX() + self.getSpeedY() * self.getSpeedY()));
        if (backDrive > 25) {
            move.setEnginePower(-0.8);
            System.out.println("поставил -0,8");
            move.setWheelTurn(-ms);
            return;
        } else {
            if (backDrive <= 25 && backDrive != 0) {
                move.setEnginePower(maxSp);
                move.setWheelTurn(ms);
                if (backDrive == 1) fag = 100;
                return;
            }
        }
        if (move.getEnginePower() < 0) {
            backDrive = 200;
            if (Math.sqrt(self.getSpeedX() * self.getSpeedX() + self.getSpeedY() * self.getSpeedY()) > 4) {
                move.setWheelTurn(ms);
                move.setEnginePower(0.7);
            } else {
                System.out.println("ещё грязно");
                move.setEnginePower(-0.8);
                move.setWheelTurn(-ms);
            }
        } else {
            System.out.println("postavil 1");
            move.setEnginePower(maxSp);
        }
    }


    private boolean pointInTile(Point b, Point a, Car self, TileType[][] tiles, int s) {
        int x0 = (int) (a.x / 800);
        int y0 = (int) (a.y / 800);
        int back = 0;
        int forward = 0;
        boolean c = true;
        Point ba;
        int i = 0;
        while (c) {
            i++;
            ba = new Point(a.x - 700 * i * Math.sin(self.getAngle() + Math.PI / 2), a.y + 700 * i * Math.cos(self.getAngle() + Math.PI / 2));
            if ((0 <= ba.x && ba.x <= s * 800 && 0 <= ba.y && ba.y <= s * 800) && tiles[((int) (ba.x / 800))][((int) (ba.y / 800))].compareTo(TileType.EMPTY) == 0)
                c = false;
        }
        int j = 0;
        c = true;
        while (c && i < j) {
            j++;
            ba = new Point(a.x + 700 * i * Math.sin(self.getAngle() + Math.PI / 2), a.y - 700 * i * Math.cos(self.getAngle() + Math.PI / 2));
            if ((0 <= ba.x && ba.x <= s * 800 && 0 <= ba.y && ba.y <= s * 800) && tiles[((int) (ba.x / 800))][((int) (ba.y / 800))].compareTo(TileType.EMPTY) == 0)
                c = false;
        }
        //  System.out.println((x0 * 800 < b.x) + " " + (b.x < (x0 + 1) * 800) + " " + (y0 * 800 < b.y) + " " + (b.y < (y0 + 1) * 800));
        // System.out.println(x0 * 800 + " " + "<" + b.x + "         " + b.x + " < " + (x0 + 1) * 800 + "       " + y0 * 800 + " < " + b.y + "          " + b.y + " < " + (x0 + 1) * 800);
        return i < j;
    }

    private boolean collapse(Car self, World world) {
        //System.out.println("engine " + self.getEnginePower());
        double x = self.getSpeedX();
        double y = self.getSpeedY();
        if (Math.sqrt(x * x + y * y) < 0.2 && world.getMyPlayer().getScore() != 0)
            return true;
        return false;
    }

    private void correctWay(Point next, Car self, TileType[][] tiles) {
        double corner = self.getAngleTo(next.x, next.y);
        if (Math.abs(corner) > Math.PI / 4) turn = corner / Math.PI;
    }

    private void initBfsMass(int s, int s1) {
        d = new int[s][s1];
        for (int i = 0; i < s; i++) {
            for (int j = 0; j < s1; j++) {
                d[i][j] = 0;
            }
        }
    }

    private void beforeCorner(Car self, double tturn) {
        if (turn == 1 && tturn != 1) {
            if ((ph < Math.PI / 4 && ph > -Math.PI / 4 && self.getX() % 800 < 400)
                    || (ph < Math.PI * 3 / 4 && ph > Math.PI / 4 && self.getX() % 800 > 400)
                    || (ph < -Math.PI / 4 && ph > -Math.PI * 3 / 4 && self.getX() % 800 < 400)
                    || (ph < -Math.PI * 3 / 4 || ph > Math.PI * 3 / 4 && self.getX() % 800 > 400)) {
                turn = 0;
                eng = 1D;
            }
            if ((ph < Math.PI / 4 && ph > -Math.PI / 4 && self.getX() % 800 < 200)
                    || (ph < Math.PI * 3 / 4 && ph > Math.PI / 4 && self.getX() % 800 > 600)
                    || (ph < -Math.PI / 4 && ph > -Math.PI * 3 / 4 && self.getX() % 800 < 200)
                    || (ph < -Math.PI * 3 / 4 || ph > Math.PI * 3 / 4 && self.getX() % 800 > 600))
                brackes = true;

        }
    }

    private void determinateCorner(double x, double y) {
        ph = Math.atan2(y, x);
    }

}