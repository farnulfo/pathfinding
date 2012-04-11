package pathfinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App {

  public static void main(String[] args) {
    String[] map1 = {
      "                  ",
      "        X         ",
      "        X         ",
      "  S     X     E   ",
      "        X         ",
      "        X         ",
      "                  ",
      "                  "};

    String[] map2 = {
      "                  ",
      "        X         ",
      "         X        ",
      "          X   E   ",
      "         S X      ",
      "          X       ",
      "         X        ",
      "                  "};

    String[] map3 = {
      "                  ",
      "                  ",
      "   XXXXXXXXX      ",
      "           X  E   ",
      "       X S X      ",
      "     XXXXXXX      ",
      "         X        ",
      "                  "};
    Map map = new Map(map3);

    System.out.println(map);
    System.out.println();
    System.out.println();
    System.out.println(map.toStringExtended());
    System.out.println(map.find('X'));

    Node start = map.find('S').get(0);
    System.out.println("Start : " + start);

    Node target = map.find('E').get(0);
    System.out.println("End : " + target);

    System.out.println(map.findAdjacent(start));

    List<Node> openList = new ArrayList<Node>();
    start.setTarget(target);
    openList.add(start);

    List<Node> closedList = new ArrayList<Node>();

    boolean stop = false;
    boolean findTarget = false;
    Node currentNode = null;

    while (!stop) {

      if (openList.isEmpty()) {
        System.out.println("No path found.");
        throw new IllegalArgumentException("No path found.");
      }

      System.out.println("Open list: " + openList);
      currentNode = getLowestFCos(openList);
      System.out.println("Lowest F Cost : " + currentNode);

      openList.remove(currentNode);
      closedList.add(currentNode);

      if (currentNode.equals(target)) {
        stop = true;
        findTarget = true;
      } else {
        List<Node> adjacents = map.findAdjacent(currentNode);
        System.out.println("Adjacents of " + currentNode + ": " + adjacents);
        for (Node node : adjacents) {
          //node.setParent(currentNode);
          node.setTarget(target);

          System.out.println("Looking at : " + node);
          if ((!node.isWalkable()) || (closedList.contains(node))) {
            // Do nothing
            System.out.println(node + " is not walkable or is in the closed list");
          } else {
            if (!openList.contains(node)) {
              openList.add(node);
              node.setParent(currentNode);
            } else if (currentNode.isBetterPathTo(node)) {
              node.setParent(currentNode);
            }
          }
        }
      }
    }

    if (findTarget) {
      Node node = currentNode;
      System.out.println("Target: " + target);
      while (node.getParent() != null) {
        Node parent = node.getParent();
        System.out.println("Node: " + parent);
        if (parent.getParent() != null) {
          map.set(parent.getX(), parent.getY(), '*');
        }
        node = parent;

      }
    }

    System.out.println(map);

  }

  private static Node getLowestFCos(List<Node> openList) {
    if (openList.size() == 1) {
      return openList.get(0);
    } else {
      Node min = openList.get(0);
      for (Node node : openList) {
        if (node.getFScore() < min.getFScore()) {
          min = node;
        }
      }
      return min;
    }
  }
}
