package javagym;

import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;

import noedit.Cell;
import noedit.Maze;
import noedit.Path;
import noedit.PathBuilder;
import noedit.Position;
import org.w3c.dom.css.Counter;

import static noedit.Cell.*;

public class Solution {

    @Nonnull
    @CheckReturnValue
    public Path solve(@Nonnull Maze maze, @Nonnull Position initialPosition) {
        assert Wall != maze.get(initialPosition):
                "Started inside a wall; this should never happen";

        System.out.println(maze.asStringAll());
        PathBuilder path = new PathBuilder(initialPosition);
        Previous prev = Previous.right;

        while (true) {

            //Alle posities bekijken die mogelijk zijn.
            Position rightPos = path.latest().right();
            Cell rightCell = maze.getOrElse(rightPos, Wall);
            Position downPos = path.latest().down();
            Cell downCell = maze.getOrElse(downPos, Wall);
            Position upPos = path.latest().up();
            Cell upCell = maze.getOrElse(upPos, Wall);
            Position leftPos = path.latest().left();
            Cell leftCell = maze.getOrElse(leftPos, Wall);
            //--TO DO:  Als er geen x in de maze zit dan moet er een break uitgevoerd worden.

            if (downCell == Wall && leftCell == Wall && rightCell == Wall && upCell == Wall) {
                break;
            }
            if (downCell == Exit) {
                path.down();
                break;
            }
            if (upCell == Exit) {
                path.up();
                break;
            }
            if (leftCell == Exit) {
                path.left();
                break;
            }
            if (rightCell == Exit) {
                path.right();
                break;
            }

            switch (prev) {

                case right: {
                    if (downCell == Open) {
                        path.down();
                        prev = Previous.down;
                        continue;
                    }
                    if (rightCell == Wall) {
                        if (upCell == Wall) {
                            if (leftCell == Wall) {
                                if (downCell == Wall) {
                                    System.out.println("WE WILL NOT COME OUT THIS MAZE!!");
                                    break;
                                } else {
                                    path.down();
                                    prev = Previous.down;
                                    continue;
                                }
                            } else {
                                path.left();
                                prev = Previous.left;
                                continue;
                            }
                        } else {
                            path.up();
                            prev = Previous.up;
                            continue;
                        }
                    } else {
                        path.right();
                        prev = Previous.right;
                        continue;
                    }
                }
                case up: {
                    if (rightCell == Open) {
                        path.right();
                        prev = Previous.right;
                        continue;
                    }
                    if (upCell == Wall) {
                        if (leftCell == Wall) {
                            if (downCell == Wall) {
                                if (rightCell == Wall) {
                                    System.out.println("WE WILL NOT COME OUT THIS MAZE!!");
                                    break;
                                } else {
                                    path.right();
                                    prev = Previous.right;
                                    continue;
                                }
                            } else {
                                path.down();
                                prev = Previous.down;
                                continue;
                            }
                        } else {
                            path.left();
                            prev = Previous.left;
                            continue;
                        }
                    } else {
                        path.up();
                        prev = Previous.up;
                        continue;
                    }
                }
                case down: {
                    if (leftCell == Open) {
                        path.left();
                        prev = Previous.left;
                        continue;
                    }
                    if (downCell == Wall) {
                        if (rightCell == Wall) {
                            if (upCell == Wall) {
                                if (leftCell == Wall) {
                                    System.out.println("WE WILL NOT COME OUT THIS MAZE!!");
                                    break;
                                } else {
                                    path.left();
                                    prev = Previous.left;
                                    continue;
                                }
                            } else {
                                path.up();
                                prev = Previous.up;
                                continue;
                            }
                        } else {
                            path.right();
                            prev = Previous.right;
                            continue;
                        }
                    } else {
                        path.down();
                        prev = Previous.down;
                        continue;
                    }

                }
                case left: {
                    if (upCell == Open) {
                        path.up();
                        prev = Previous.up;
                        continue;
                    }
                    if (leftCell == Wall) {
                        if (downCell == Wall) {
                            if (rightCell == Wall) {
                                if (upCell == Wall) {
                                    System.out.println("WE WILL NOT COME OUT THIS MAZE!!");
                                    break;
                                } else {
                                    path.up();
                                    prev = Previous.up;
                                    continue;
                                }
                            } else {
                                path.right();
                                prev = Previous.right;
                                continue;
                            }
                        } else {
                            path.down();
                            prev = Previous.down;
                            continue;
                        }
                    } else {
                        path.left();
                        prev = Previous.left;
                        continue;
                    }


                }
            }



        }
        return path.build();
    }
}

enum Previous {
    left, right, up, down;
}
