// Alvin Tay Ming Hwee
// A0218390E

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class Galactic {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out) ));
    String[] temp = br.readLine().split(" ");
    int n = Integer.parseInt(temp[0]);
    int m = Integer.parseInt(temp[1]);

    Score[] arr = new Score[n+1];
    BBST bbst = new BBST();

    for (int i = 1; i<n+1; i++) {
      arr[i] = new Score(i);
      bbst.insert(arr[i]);     
    }
    for (int i = 0; i<m; i++) {
      String[] line = br.readLine().split(" ");
      int team = Integer.parseInt(line[0]);
      int penalty = Integer.parseInt(line[1]);
      bbst.delete(arr[team]);
      arr[team].win(penalty);
      bbst.insert(arr[team]);
      pw.println(bbst.rank(bbst.root, arr[1]));
    }
    br.close();
    pw.close();
  }
}

class Score implements Comparable<Score> {
  int team;
  int wins;
  int penalty;

  public Score(int team) {
    this.team = team;
    this.wins = 0;
    this.penalty = 0;
  }

  public void win(int penalty) {
    wins++;
    this.penalty+=penalty;
  }

  public int compareTo(Score other){
    if (this.wins > other.wins) return -1;
    else if (this.wins < other.wins) return 1;
    if (this.penalty > other.penalty) return 1;
    else if (this.penalty < other.penalty) return -1;
    else return this.team - other.team;
  }
}

class BSTVertex {
  public BSTVertex parent, left, right;
  public Score key;
  public int height;
  public int size;

  public BSTVertex(Score v) { 
    key = v; 
    parent = left = right = null; 
    height = 0; 
    size = 1;
  }

  
  public void updateHeightSize() {
    if (this.left == null && this.right == null) {
      this.height = 0;
      this.size = 1;
    } else if (this.left == null) {
      this.height = this.right.height + 1;
      this.size = this.right.size + 1;
    } else if (this.right == null) {
      this.height = this.left.height + 1;
      this.size = this.left.size + 1;
    } else {
      this.height = Math.max(this.left.height, this.right.height) + 1;
      this.size = this.left.size + this.right.size + 1;
    }
  }
}

class BBST {
  public BSTVertex root;

  public BBST() { root = null; }

  public BSTVertex search(BSTVertex T, Score v) {
    if (T == null)  return null;                     // not found
    int compare = v.compareTo(T.key);
    if (compare < 0) return search(T.left, v);        // search to the left
    else if (compare > 0) return search(T.right, v);  // search to the right
    else return T;                                    // found
  }
  
  public Score findMin(BSTVertex T) {
    if (T.left == null) return T.key;                    // this is the min
    return findMin(T.left);                              // go to the left
  }

  public Score findMax(BSTVertex T) {
    if (T.right == null) return T.key;                   // this is the max
    return findMax(T.right);                             // go to the right
  }

  public Score successor(Score v) { 
    BSTVertex vPos = search(root, v);
    return vPos == null ? null : successor(vPos);
  }

  public Score successor(BSTVertex T) {
    if (T.right != null)                                // this subtree has right subtree
      return findMin(T.right);                          // the successor is the minimum of right subtree
    else {
      BSTVertex par = T.parent;
      BSTVertex cur = T;
      // if par(ent) is not root and cur(rent) is its right children
      while ((par != null) && (cur == par.right)) {
        cur = par;                                      // continue moving up
        par = cur.parent;
      }
      return par == null ? null : par.key;              // this is the successor of T
    }
  }

  public Score predecessor(Score v) { 
    BSTVertex vPos = search(root, v);
    return vPos == null ? null : predecessor(vPos);
  }

  // helper recursive method to find predecessor to for a given vertex T in BST
  public Score predecessor(BSTVertex T) {
    if (T.left != null)                         // this subtree has left subtree
      return findMax(T.left);  // the predecessor is the maximum of left subtree
    else {
      BSTVertex par = T.parent;
      BSTVertex cur = T;
      // if par(ent) is not root and cur(rent) is its left children
      while ((par != null) && (cur == par.left)) { 
        cur = par;                                         // continue moving up
        par = cur.parent;
      }
      return par == null ? null : par.key;           // this is the successor of T
    }
  }

  // public method called to perform inorder traversal
  public void inorder() { 
    inorder(root);
    System.out.println();
  }

  // helper method to perform inorder traversal
  public void inorder(BSTVertex T) {
    if (T == null) return;
    inorder(T.left);                               // recursively go to the left
    System.out.printf(" %d", T.key);                      // visit this BST node
    inorder(T.right);                             // recursively go to the right
  }

  // public method called to insert a new key with value v into BST
  public void insert(Score v) { root = insert(root, v); }

  // helper recursive method to perform insertion of new vertex into BST
  public BSTVertex insert(BSTVertex T, Score v) {
    if (T == null) return new BSTVertex(v);          // insertion point is found

    int compare = v.compareTo(T.key);
    if (compare > 0) {                                      // search to the right
      T.right = insert(T.right, v);
      T.right.parent = T;
    } else {                                                 // search to the left
      T.left = insert(T.left, v);
      T.left.parent = T;
    }
    
    if (T != null) {
      T.updateHeightSize();
      T = balance(T);
    }
    return T;                                          // return the updated BST
  }  
  
  public BSTVertex leftRotate(BSTVertex T) {
    if (T == null) return T;
    BSTVertex w = T.right;
    if (w == null) return w;
    w.parent = T.parent;
    if (T.parent == null) root = w;     // w becomes the new root
    T.parent = w;
    T.right = w.left;
    if (w.left != null) w.left.parent= T;
    w.left = T;

    T.updateHeightSize();
    w.updateHeightSize();

    return w;
  }

  public BSTVertex rightRotate(BSTVertex T) {
    if (T == null) return T;
    BSTVertex w = T.left;
    if (w == null) return w;
    w.parent = T.parent;
    if (T.parent == null) root = w;     // w becomes the new root
    T.parent = w;
    T.left = w.right;
    if (w.right != null) w.right.parent = T;
    w.right = T;
    
    T.updateHeightSize();
    w.updateHeightSize();
    
    return w;
  }

  public BSTVertex balance(BSTVertex T) {
    int bf = balanceFactor(T);
    if (bf == 2) {
      if (balanceFactor(T.left) == -1) {
        T.left = leftRotate(T.left);
      }
      T = rightRotate(T);
    } else if (bf == -2) {
      if (balanceFactor(T.right) == 1) {
        T.right = rightRotate(T.right);
      }
      T = leftRotate(T);
    }
    return T;
  }
  

  public int balanceFactor(BSTVertex T) {
    if (T == null) return 0;
    int left = -1;
    int right = -1;
    if (T.left != null) left = T.left.height;
    if (T.right != null) right = T.right.height;

    return left - right;
  }



  // public method to delete a vertex containing key with value v from BST
  public void delete(Score v) { root = delete(root, v); }

  // helper recursive method to perform deletion 
  public BSTVertex delete(BSTVertex T, Score v) {
    if (T == null)  return T;              // cannot find the item to be deleted
    int compare = v.compareTo(T.key);
    if (compare > 0) {                                    // search to the right
      T.right = delete(T.right, v);
    }
    else if (compare < 0) {                               // search to the left
      T.left = delete(T.left, v);
    }
    else {                                              // this is the node to be deleted
      if (T.left == null && T.right == null) {          // this is a leaf 
        T = null;                                       // simply erase this node
      } else if (T.left == null && T.right != null) {   // only one child at right        
        T.right.parent = T.parent;
        T = T.right;                                     // bypass T
      } else if (T.left != null && T.right == null) {    // only one child at left        
        T.left.parent = T.parent;
        T = T.left;                                      // bypass T
      } else {                                  // has two children, find successor
        Score successorV = successor(v);
        T.key = successorV;                     // replace this key with the successor's key
        T.right = delete(T.right, successorV);  // delete the old successorV
      }
    }
    if (T != null) { // if previously was not a leaf
      T.updateHeightSize();
      T = balance(T);
    }
    return T;                                          // return the updated BST
  }

  public int rank(BSTVertex T, Score v) {
    int left = 0;
    if (T.left != null) left = T.left.size;
    if (T.key == v) {
      return left + 1;
    } else if (v.compareTo(T.key) < 0) {
      return rank(T.left, v);
    } else {
      return rank(T.right, v) + left + 1;
    }
  }
}