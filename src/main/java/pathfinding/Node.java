/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pathfinding;

/**
 *
 * @author farnulfo
 */
public class Node {

  private int x, y;
  private char c;
  private Node parent;
  private Node target;
  private int costMovementFromParent = 0;
  private int estimatedCostMovementToEnd = 0;

  public Node(char c, int x, int y) {
    this.x = x;
    this.y = y;
    this.c = c;
  }

  /**
   * @return the x
   */
  public int getX() {
    return x;
  }

  /**
   * @return the y
   */
  public int getY() {
    return y;
  }
  
  public boolean isWalkable() {
    return c != 'X';
  }

  @Override
  public String toString() {
    return new StringBuilder()
            .append("('")
            .append(c)
            .append("',")
            .append(x)
            .append(",")
            .append(y)
            .append(",parentNode=")
            .append(parent)
            .append(",costMovementFromParent=")
            .append(costMovementFromParent)
            .append(")")
            .toString();
  }

  @Override
  public int hashCode() {
    int hash = 5;
    hash = 61 * hash + this.x;
    hash = 61 * hash + this.y;
    return hash;
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof Node)) {
      return false;
    }
    Node p = (Node) o;
    return p.x == x && p.y == y && p.c == c;
  }

  void setParent(Node parent) {
    this.parent = parent;
    calculateCostMovementFromParent();
  }

  private void calculateCostMovementFromParent() {
    if ((parent.x == x) || (parent.y == y))  {
      costMovementFromParent = 10;
    } else {
      costMovementFromParent = 14;
    }
    
  }

  private int manhattanDistance(Node end) {
    int x1 = Math.abs(end.getX() - this.x);
    int y1 = Math.abs(end.getY() - this.y);
    
    return (x1+y1) * 10;
  }
  
  public int getFScore() {
    return this.costMovementFromParent + this.estimatedCostMovementToEnd;
  }

  /**
   * @return the target
   */
  public Node getTarget() {
    return target;
  }

  /**
   * @param target the target to set
   */
  public void setTarget(Node target) {
    this.target = target;
    estimatedCostMovementToEnd = manhattanDistance(target);
  }

  boolean isBetterPathTo(Node node) {
    int costToGoToNode = ((x == node.x) || (y == node.y)) ? 10 : 14;
    
    if ((costMovementFromParent + costToGoToNode) < node.costMovementFromParent) {
      return true;
    } else {
      return false;
    }
  }

  Node getParent() {
    return parent;
  }
}
