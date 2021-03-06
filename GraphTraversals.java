import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
 
//@author Sada Kurapati <sadakurapati@gmail.com>
public class GraphTraversals {
 
  public static final Node<String> targetNode = new Node<String>("V");
  public static final Node<String> targetTreeNode = new Node<String>("D");
 
  public static void main(String args[]) {
    //build sample graph.
    Node<String> source = getSampleGraph();
    Node<String> root = getSampleTree();
    graphBFSByQueue(source);
    //graphBFSByLevelList(source);
    //treeBFSByQueue(root);
    //treeBFSByLevelList(root);
    graphDFSByRecersion(source);
    graphDFSByStack(source);
    //treeDFSByRecersion(root);
    treeDFSByStack(root);
 
  }
 
  public static void graphBFSByQueue(Node<String> source) {
    //if empty graph, then return.
    if (null == source) {
      return;
    }
    Queue<Node<String>> queue = new LinkedList<Node<String>>();
    //add source to queue.
    queue.add(source);
    visitNode(source);
    source.visited = true;
    while (!queue.isEmpty()) {
      Node<String> currentNode = queue.poll();
      //check if we reached out target node
      if (currentNode.equals(targetNode)) {
        return; // we have found our target node V.
      }
      //Add all of unvisited neighbors to the queue. We add only unvisited nodes to avoid cycles.
      for (Node<String> neighbor : currentNode.neighbors) {
        if (!neighbor.visited) {
          visitNode(neighbor);
          neighbor.visited = true; //mark it as visited
          queue.add(neighbor);
        }
      }
    }
  }
 
  public static void graphBFSByLevelList(Node<String> source) {
    //if empty graph, then return.
    if (null == source) {
      return;
    }
    Set<Node<String>> frontier = new HashSet<Node<String>>();
    //this will be useful to identify what we visited so far and also its level
    //if we dont need level, we could just use a Set or List
    HashMap<Node<String>, Integer> level = new HashMap<Node<String>, Integer>();
    int moves = 0;
    //add source to frontier.
    frontier.add(source);
    visitNode(source);
    level.put(source, moves);
    while (!frontier.isEmpty()) {
      Set<Node<String>> next = new HashSet<Node<String>>();
      for (Node<String> parent : frontier) {
        for (Node<String> neighbor : parent.neighbors) {
          if (!level.containsKey(neighbor)) {
            visitNode(neighbor);
            level.put(neighbor, moves);
            next.add(neighbor);
          }
          //check if we reached out target node
          if (neighbor.equals(targetNode)) {
            return; // we have found our target node V.
          }
        }//inner for
      }//outer for
      moves++;
      frontier = next;
    }//while
  }
 
  public static void treeBFSByQueue(Node<String> root) {
    //if empty graph, then return.
    if (null == root) {
      return;
    }
    Queue<Node<String>> queue = new LinkedList<Node<String>>();
    //add root to queue.
    queue.add(root);
    while (!queue.isEmpty()) {
      Node<String> currentNode = queue.poll();
      visitNode(currentNode);
      //check if we reached out target node
      if (currentNode.equals(targetTreeNode)) {
        return; // we have found our target node V.
      }
      //Add all of unvisited neighbors to the queue. We add only unvisited nodes to avoid cycles.
      for (Node<String> neighbor : currentNode.neighbors) {
        queue.add(neighbor);
      }
    }
  }
 
  public static void treeBFSByLevelList(Node<String> root) {
    //if empty graph, then return.
    if (null == root) {
      return;
    }
    List<Node<String>> frontier = new ArrayList<Node<String>>();
    //add root to frontier.
    frontier.add(root);
    while (!frontier.isEmpty()) {
      List<Node<String>> next = new ArrayList<Node<String>>();
      for (Node<String> parent : frontier) {
        visitNode(parent);
        //check if we reached out target node
        if (parent.equals(targetTreeNode)) {
          return; // we have found our target node V.
        }
 
        for (Node<String> neighbor : parent.neighbors) {
          next.add(neighbor);
        }//inner for
      }//outer for
      frontier = next;
    }//while
  }
 
  public static void graphDFSByRecersion(Node<String> currentNode) {
    if (null == currentNode) {
      return; // back track
    }
    visitNode(currentNode);
    currentNode.visited = true;
    //check if we reached out target node
    if (currentNode.equals(targetNode)) {
      return; // we have found our target node V.
    }
    //recursively visit all of unvisited neighbors
    for (Node<String> neighbor : currentNode.neighbors) {
      if (!neighbor.visited) {
        graphDFSByRecersion(neighbor);
      }
    }
  }
 
  public static void graphDFSByStack(Node<String> source) {
    //if empty graph, return
    if (null == source) {
      return; //
    }
    Stack<Node<String>> stack = new Stack<Node<String>>();
    //add source to stack
    stack.push(source);
    while (!stack.isEmpty()) {
      Node<String> currentNode = stack.pop();
      visitNode(currentNode);
      currentNode.visited = true;
      //check if we reached out target node
      if (currentNode.equals(targetTreeNode)) {
        return; // we have found our target node V.
      }
      //add all of unvisited nodes to stack
      for (Node<String> neighbor : currentNode.neighbors) {
        if (!neighbor.visited) {
          stack.push(neighbor);
        }
      }
    }
  }
 
  public static void treeDFSByRecersion(Node<String> currentNode) {
    if (null == currentNode) {
      return; // back track
    }
    visitNode(currentNode);
    //check if we reached out target node
    if (currentNode.equals(targetNode)) {
      return; // we have found our target node V.
    }
    //recursively visit all of unvisited neighbors
    for (Node<String> neighbor : currentNode.neighbors) {
              graphDFSByRecersion(neighbor);
    }
  }
 
  public static void treeDFSByStack(Node<String> source) {
    //if empty graph, return
    if (null == source) {
      return; //
    }
    Stack<Node<String>> stack = new Stack<Node<String>>();
    //add source to stack
    stack.push(source);
    while (!stack.isEmpty()) {
      Node<String> currentNode = stack.pop();
      visitNode(currentNode);
      //check if we reached out target node
      if (currentNode.equals(targetTreeNode)) {
        return; // we have found our target node V.
      }
      //add all of unvisited nodes to stack
      for (Node<String> neighbor : currentNode.neighbors) {
          stack.push(neighbor);
      }
    }
  }
 
  public static void visitNode(Node<String> node) {
    System.out.printf(" %s ", node.data);
  }
 
  public static Node<String> getSampleGraph() {
    //building sample graph.
    Node<String> S = new Node<String>("S");
    Node<String> A = new Node<String>("A");
    Node<String> C = new Node<String>("C");
    Node<String> B = new Node<String>("B");
    Node<String> D = new Node<String>("D");
    Node<String> E = new Node<String>("E");
    Node<String> F = new Node<String>("F");
    Node<String> V = new Node<String>("V");
 
    S.neighbors.add(A);
    S.neighbors.add(C);
 
    A.neighbors.add(S);
    A.neighbors.add(B);
 
    B.neighbors.add(A);
 
    C.neighbors.add(S);
    C.neighbors.add(D);
    C.neighbors.add(E);
 
    D.neighbors.add(C);
    D.neighbors.add(E);
    D.neighbors.add(F);
 
    E.neighbors.add(C);
    E.neighbors.add(D);
    E.neighbors.add(F);
    E.neighbors.add(V);
 
    F.neighbors.add(D);
    F.neighbors.add(E);
    F.neighbors.add(V);
 
    V.neighbors.add(E);
    V.neighbors.add(F);
 
    return S;
  }
 
  public static Node<String> getSampleTree() {
    //building sample Tree.
    Node<String> A = new Node<String>("A");
    Node<String> B = new Node<String>("B");
    Node<String> C = new Node<String>("C");
    Node<String> D = new Node<String>("D");
    Node<String> E = new Node<String>("E");
    Node<String> F = new Node<String>("F");
    Node<String> G = new Node<String>("G");
    Node<String> H = new Node<String>("H");
 
    A.neighbors.add(B);
    A.neighbors.add(C);
    A.neighbors.add(D);
 
    B.neighbors.add(E);
    B.neighbors.add(F);
    B.neighbors.add(G);
 
    C.neighbors.add(H);
 
    return A;
  }
 
}
 
class Node<T> {
 
  T data;
  ArrayList<Node<T>> neighbors = null;
  boolean visited = false;
 
  Node(T value) {
    data = value;
    neighbors = new ArrayList<Node<T>>();
  }
 
  @Override
  public int hashCode() {
    int hash = 5;
    return hash;
  }
 
  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final Node<?> other = (Node<?>) obj;
    if (this.data != other.data && (this.data == null || !this.data.equals(other.data))) {
      return false;
    }
    return true;
  }
}
