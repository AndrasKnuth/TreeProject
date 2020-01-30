package TreeProject;

import java.util.ArrayList;
import java.util.List;

public class Node<N> {

    private N data = null;

    private List<Node<N>> children = new ArrayList<>();

    private Node<N> parent = null;

    public Node(N data) {
        this.data = data;
    }

    public Node<N> addChild(Node<N> child) {
        child.setParent(this);
        this.children.add(child);
        return child;
    }

    public void addChildren(List<Node<N>> children) {
        children.forEach(each -> each.setParent(this));
        this.children.addAll(children);
    }

    public List<Node<N>> getChildren() {
        return children;
    }

    public N getData() {
        return data;
    }

    public void setData(N data) {
        this.data = data;
    }

    private void setParent(Node<N> parent) {
        this.parent = parent;
    }

    public Node<N> getParent() {
        return parent;
    }

    public Node<N> getRoot() {
        if(parent == null){
            return this;
        }
        return parent.getRoot();
    }

    public void deleteNode() {
        if (parent != null) {
            int index = this.parent.getChildren().indexOf(this);
            this.parent.getChildren().remove(this);
            for (Node<N> each : getChildren()) {
                each.setParent(this.parent);
            }
            this.parent.getChildren().addAll(index, this.getChildren());
        } else {
            deleteRootNode();
        }
        this.getChildren().clear();
    }

    public Node<N> deleteRootNode() {
        if (parent != null) {
            throw new IllegalStateException("deleteRootNode is not called on root");
        }
        Node<N> newParent = null;
        if (!getChildren().isEmpty()) {
            newParent = getChildren().get(0);
            newParent.setParent(null);
            getChildren().remove(0);
            for (Node<N> each : getChildren()) {
                each.setParent(newParent);
            }
            newParent.getChildren().addAll(getChildren());
        }
        this.getChildren().clear();
        return newParent;
    }
}
