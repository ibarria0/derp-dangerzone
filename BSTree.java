import java.util.*;
import java.io.*;

public class BSTree<T extends Comparable>{
	private Node<Word> root;

	public BSTree(){
		this.root = null;
	}

	public Node root(){
		return root;
	}

	public boolean isEmpty(){
		if(root == null)
			return true;
		return false;
	}


	public int countNodes(){
		return countNodes(root);
	}

	public boolean delete(Word t){
		Node temp;
		Node ptr = findNode(t);
		if(ptr == null)
			return false;
		if(ptr.left() == null){
			ptr.setData(ptr.right().data());
			ptr.setRight(ptr.right().right());
			ptr.clearRight();
			return true;
		}
		else if(ptr.right() == null){
			ptr.setData(ptr.left().data());
			ptr.setLeft(ptr.left().right());
			ptr.clearLeft();
			return true;
		}
		else{
			temp = ptr.right();
			while(temp.left() != null){
				temp = temp.left();
			}
			ptr.setData(temp.data());
			ptr.setLeft(temp.left().right());
			ptr.setRight(temp.right().right());
			return delete(ptr.right(), temp.data());
		}
	}

	public boolean delete(Node ptr, Word t){
		Node temp;
		if(ptr == null)
			return false;
		if(ptr.left() == null){
			ptr.setData(ptr.right().data());
			ptr.clearRight();
			return true;
		}
		else if(ptr.right() == null){
			ptr.setData(ptr.left().data());
			ptr.clearLeft();
			return true;
		}
		else{
			temp = ptr.right();
			while(temp.left() != null)
				temp = temp.left();
			ptr.setData(temp.data());
			return delete(ptr.right(), temp.data());
		}
	}



	public int countNodes(Node node){
		int count = 1;
		if(node.left() != null)
			count += countNodes(node.left());
		if(node.right() != null)
			count += countNodes(node.right());
		return count;
	}

	public void postOrder(){
		System.out.println("Post Order Traversal");
		postOrder(this.root);
		System.out.println("END OF TREE\n");
	}

	public boolean postOrder(Node node){
		if(node == null )
			return true;
		postOrder(node.left());
		postOrder(node.right());
		visit(node);
		return true;
	}

	public boolean clearOrder(){
		Node node = root;
		clearOrder(node.left());
		clearOrder(node.right());
		clear(node);
		return true;
	}
	public boolean clearOrder(Node node){
		clearOrder(node.left());
		clearOrder(node.right());
		clear(node);
		return true;
	}

	public void clear(Node node){
		node.clear();
	}

	public void preOrder(){
		System.out.println("Pre Order Traversal");
		preOrder(this.root);
		System.out.println("END OF TREE\n");
	}

	public boolean preOrder(Node<Word> node){
		if(node == null )
			return true;
		visit(node);
		preOrder(node.left());
		preOrder(node.right());
		return true;
	}
	
	public void inOrder(){
		System.out.println("In Order Traversal");
		inOrder(this.root);
		System.out.println("END OF TREE\n");
	}

	public boolean inOrder(Node<Word> node){
		if(node == null )
			return true;
		inOrder(node.left());
		visit(node);
		inOrder(node.right());
		return true;
	}

	public void visit(Node<Word> node){
		System.out.println(node.data());
	}

	public Node findNode(Word word){
		return findNode(root,word);

	}

	public Node findNode(Node node, Word t){
		if(node == null)
			return null;
		if(node.data().compareTo(t) == 0){
			System.out.println("Found");
			return node;
		}
		else if(node.compareTo(t) > 0)
			return findNode(node.left(), t);
		else if(node.compareTo(t) < 0)
			return findNode(node.right(), t);
		return null;
	}


	public boolean insertNode(Word data){
		if(isEmpty()){
			root = new Node(data);
			return true;
		}
		return insertNode(this.root,data);
	}

	private boolean insertNode(Node<Word> root, Word word){
		if(word.compareTo(root.data()) < 0){
			if(root.left() == null){
				root.setLeft(new Node(word));
				return true;
			}
			else
				return insertNode(root.left(), word);
		}
		else if(word.compareTo(root.data()) >= 0){
			if(root.right() == null){
				root.setRight(new Node(word));
				return true;
			}
			else
				return insertNode(root.right(), word);
		}
		return false;
	}


}


