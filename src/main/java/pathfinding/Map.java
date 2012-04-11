/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pathfinding;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author farnulfo
 */
class Map {

  private char[][] map;

  public Map(String... lines) {
    if (lines == null) {
      throw new IllegalArgumentException("lines cannot be null.");
    }

    int height = lines.length;
    int width = lines[0].length();
    map = new char[height][width];

    int row = 0;
    for (String s : lines) {
      for (int column = 0; column < s.length(); column++) {
        char c = s.charAt(column);
        if (isValidCharacter(c)) {
          map[row][column] = c;
        } else {
          throw new IllegalArgumentException("Illegal character '" + c + "'");
        }
      }
      row++;
    }

  }

  public int getWidth() {
    return map[0].length;
  }

  public int getHeigth() {
    return map.length;
  }

  private boolean isValidCharacter(char c) {
    if ((c == ' ') || (c == 'S') || (c == 'E') || (c == 'X')) {
      return true;
    } else {
      return false;
    }
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < map.length; i++) {
      for (int j = 0; j < map[i].length; j++) {
        sb.append(map[i][j]);
      }
      sb.append(System.getProperty("line.separator"));
    }
    return sb.toString();
  }

  public String toStringExtended() {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < map.length; i++) {
      for (int j = 0; j < map[i].length; j++) {
        if ((i == 0) && (j == 0)) {
          sb.append(" |");
          for (int k = 0; k < map[i].length; k++) {
            sb.append(k % 10);
            sb.append('|');
          }
          sb.append(System.getProperty("line.separator"));
          for (int k = 0; k <= map[i].length; k++) {
            sb.append("--");
          }
          sb.append(System.getProperty("line.separator"));
        }
        if (j == 0) {
          sb.append(i % 10);
          sb.append('|');
        }
        sb.append(map[i][j]);
        sb.append('|');
      }
      sb.append(System.getProperty("line.separator"));
      for (int k = 0; k <= map[i].length; k++) {
        sb.append("--");
      }
      sb.append(System.getProperty("line.separator"));
    }
    return sb.toString();
  }

  public List<Node> find(char c) {
    List<Node> points = new ArrayList<Node>();
    for (int i = 0; i < map.length; i++) {
      for (int j = 0; j < map[i].length; j++) {
        if (map[i][j] == c) {
          points.add(new Node(map[i][j], j, i));
        }
      }
    }
    return points;
  }

  List<Node> findAdjacent(Node point) {
    checkNode(point);
    int width = getWidth();
    int height = map.length;
    List<Node> points = new ArrayList<Node>();
    for (int i = -1; i <= 1; i++) {
      for (int j = -1; j <= 1; j++) {
        if (!((i == 0) && (j == 0))) {
          final int x = point.getX() + j;
          final int y = point.getY() + i;
          if (isInside(x, y)) {
            Node p = new Node(map[y][x], x, y);
            points.add(p);
          }
        }
      }
    }
    return points;
  }

  private void checkNode(Node point) {
    if ((point.getX() > getWidth()) || (point.getY() > map.length)) {
      throw new IllegalArgumentException();
    }
  }

  public boolean isInside(int x, int y) {
    boolean insideX = (x >= 0) && (x < map[0].length);
    boolean insideY = (y >= 0) && (y < map.length);

    return insideX && insideY;
  }
  
  public void set(int x, int y, char c) {
    if (isInside(x, y)) {
      map[y][x] = c;
    }
  }
}
