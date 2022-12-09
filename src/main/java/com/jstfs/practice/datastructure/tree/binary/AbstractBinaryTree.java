package com.jstfs.practice.datastructure.tree.binary;

import java.util.List;

import com.jstfs.common.utils.MyCollectionUtils;
import com.jstfs.common.utils.MyStringUtils;

/**
 * 抽象的二叉树实现
 * 
 * @createBy	落叶
 * @createTime	2022年10月30日 下午12:44:56
 */
public abstract class AbstractBinaryTree implements ITree {
	private TreeNode 			rootNode;					//根节点
	private TreeNode 			headNode;					//头结点,其左子结点指向根节点,用于前序和中序线索化遍历
	
	private TreeNode 			preNode;					//用于二叉树线索化或者遍历线索化二叉树时,保存上一个操作过的节点
	private TreeErgodicTypeEnum threadedErgodicType = null;	//记录使用了哪种遍历顺序线索化
	
	/**
	 * 获得根节点
	 */
	@Override
	public TreeNode getRootNode() {
		return rootNode;
	}
	
	/**
	 * 设置根节点
	 */
	@Override
	public void setRootNode(TreeNode rootNode) {
		this.rootNode = rootNode;
	}
	
	/**
	 * 获得头节点
	 */
	@Override
	public TreeNode getHeadNode() {
		return headNode;
	}
	
	/**
	 * 设置头节点
	 */
	@Override
	public void setHeadNode(TreeNode headNode) {
		this.headNode = headNode;
	}
	
	/**
	 * 构建一棵二叉树
	 */
	@Override
	public void build(Object[] dataAry) {
		for(int i = 0; i < dataAry.length; i++) {
			addNode(new TreeNode(dataAry[i]));
		}
	}
	
	/**
	 * 遍历二叉树(默认使用中序遍历)
	 */
	@Override
	public void ergodic() {
		ergodic(TreeErgodicTypeEnum.ERGODIC_LDR);
	}
	
	/**
	 * 根据指定的遍历方式遍历二叉树
	 * 
	 * @param typeEnum	指定的遍历方式
	 */
	public void ergodic(TreeErgodicTypeEnum typeEnum) {
		String checkResult = check(typeEnum);
		if(MyStringUtils.isNotEmpty(checkResult)) {
			throw new RuntimeException(checkResult);
		}
		
		if(typeEnum.getType().equals(TreeErgodicTypeEnum.ERGODIC_DLR.getType())) {
			//前序遍历
			ergodicDlr(rootNode);
		} else if(typeEnum.getType().equals(TreeErgodicTypeEnum.ERGODIC_LDR.getType())) {
			//中序遍历
			ergodicLdr(rootNode);
		} else if(typeEnum.getType().equals(TreeErgodicTypeEnum.ERGODIC_LRD.getType())) {
			//后序遍历
			ergodicLrd(rootNode);
		} else {
			throw new RuntimeException("不支持的遍历方式:" + typeEnum.getType());
		}
	}
	
	/**
	 * 线索化二叉树(默认使用前序遍历)
	 */
	public void threaded() {
		threaded(TreeErgodicTypeEnum.ERGODIC_LDR);
	}
	
	/**
	 * 根据指定的遍历方式线索化二叉树
	 * 
	 * @param typeEnum 指定的遍历方式
	 */
	public void threaded(TreeErgodicTypeEnum typeEnum) {
		String checkResult = check(typeEnum);
		if(MyStringUtils.isNotEmpty(checkResult)) {
			throw new RuntimeException(checkResult);
		}
		
		if(typeEnum.getType().equals(TreeErgodicTypeEnum.ERGODIC_DLR.getType())) {
			//前序线索化
			threadedDlr(rootNode);
		} else if(typeEnum.getType().equals(TreeErgodicTypeEnum.ERGODIC_LDR.getType())) {
			//中序线索化
			threadeLdr(rootNode);
		} else if(typeEnum.getType().equals(TreeErgodicTypeEnum.ERGODIC_LRD.getType())) {
			//后序线索化
			threadeLrd(rootNode);
		} else {
			throw new RuntimeException("不支持的遍历方式:" + typeEnum.getType());
		}
		
		//线索化最后一个节点:将其右节点指向head节点
		if(preNode.getRightChild() == null) {
			preNode.setRightChild(headNode);
			preNode.setRightThreadFlag(true);
		}
		
		//记录线索化的顺序
		threadedErgodicType = typeEnum;
	}
	
	/**
	 * 遍历线索二叉树(只能使用线索化时指定的遍历方式[threadedErgodicType]来遍历)
	 */
	public void ergodicThreaded() {
		String checkResult = check(threadedErgodicType);
		if(MyStringUtils.isNotEmpty(checkResult)) {
			throw new RuntimeException(checkResult);
		}
		
		if(threadedErgodicType.getType().equals(TreeErgodicTypeEnum.ERGODIC_DLR.getType())) {
			//前序遍历
			ergodicThreadedDlr();
		} else if(threadedErgodicType.getType().equals(TreeErgodicTypeEnum.ERGODIC_LDR.getType())) {
			//中序遍历
			ergodicThreadedLdr();
		} else if(threadedErgodicType.getType().equals(TreeErgodicTypeEnum.ERGODIC_LRD.getType())) {
			//后序遍历
			ergodicThreadedLrd();
		} else {
			throw new RuntimeException("不支持的遍历方式:" + threadedErgodicType.getType());
		}
	}
	
	/**
	 * 查找指定节点(默认使用中序遍历来查找)
	 * 
	 * @param data	要查找的节点的数据
	 */
	@Override
	public TreeNode searchNode(Object data) {
		return searchNode(TreeErgodicTypeEnum.ERGODIC_LDR, data);
	}
	
	/**
	 * 根据指定的查找顺序查找指定节点
	 * 
	 * @param typeEnum	查找的顺序
	 * @param data		要查找的节点的数据
	 */
	@Override
	public TreeNode searchNode(TreeErgodicTypeEnum typeEnum, Object data) {
		String checkResult = check(typeEnum);
		if(MyStringUtils.isNotEmpty(checkResult)) {
			throw new RuntimeException(checkResult);
		}
		
		if(typeEnum.getType().equals(TreeErgodicTypeEnum.ERGODIC_DLR.getType())) {
			//前序查找
			return searchDlr(rootNode, data);
		} else if(typeEnum.getType().equals(TreeErgodicTypeEnum.ERGODIC_LDR.getType())) {
			//中序查找
			return searchLdr(rootNode, data);
		} else if(typeEnum.getType().equals(TreeErgodicTypeEnum.ERGODIC_LRD.getType())) {
			//后序查找
			return searchLrd(rootNode, data);
		} else {
			throw new RuntimeException("不支持的遍历方式:" + typeEnum.getType());
		}
	}
	
	/**
	 * 删除数据
	 * 
	 * 默认实现是连同以该节点为根节点的整个子树都删除
	 * 
	 * @param data	要删除的数据
	 * @return		是否删除成功
	 */
	@Override
	public boolean deleteNode(Object data) {
		if(rootNode == null) {
			System.out.println("空树不能删除");
			return false;
		}
		
		TreeNode delNode = searchNode(data);
		if(delNode == null) {
			System.out.println("要删除的节点[" + data + "]不存在");
			return false;
		}
		
		return deleteNode(delNode);
	}
	
	/**
	 * 删除节点
	 * 
	 * 默认实现是连同以该节点为根节点的整个子树都删除
	 * 
	 * @param delNode	要删除的节点
	 * @return			是否删除成功
	 */
	@Override
	public boolean deleteNode(TreeNode delNode) {
		if(rootNode == null) {
			System.out.println("空树不能删除");
			return false;
		}
		
		if(rootNode == delNode) {
			System.out.println("删除根节点");
			rootNode = null;
			return true;
		}
		
		return deleteNode(rootNode, delNode);
	}
	
	/**
	 * 打印树
	 */
	public void printTree() {
		if(rootNode == null) {
			System.out.println("空树");
			return;
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append("[").append(rootNode.printNode()).append("]").append("\n");
		
		List<TreeNode> nodes = MyCollectionUtils.newList(TreeNode.class);
		nodes.add(rootNode);
		
		printTree(nodes, sb);
		
		System.out.println(sb);
	}
	
	/**
	 * 获得树的高度
	 */
	@Override
	public int getLever() {
		if(rootNode == null) {
			return 0;
		}
		
		return rootNode.getLever();
	}
	
//======================================= public/private 方法分界线 TODO =========================================================================
	
	private String check(TreeErgodicTypeEnum typeEnum) {
		if(rootNode == null) {
			return "二叉树为空";
		}
		if(typeEnum == null) {
			return "遍历方式不能为空";
		}
		return null;
	}
	
	/**
	 * 前序遍历
	 */
	private void ergodicDlr(TreeNode node) {
		ergodicAction(node);
		
		if(node.getLeftChild() != null) {
			ergodicDlr(node.getLeftChild());
		}
		
		if(node.getRightChild() != null) {
			ergodicDlr(node.getRightChild());
		}
	}
	
	/**
	 * 中序遍历
	 */
	private void ergodicLdr(TreeNode node) {
		if(node.getLeftChild() != null) {
			ergodicLdr(node.getLeftChild());
		}
		
		ergodicAction(node);
		
		if(node.getRightChild() != null) {
			ergodicLdr(node.getRightChild());
		}
	}
	
	/**
	 * 后序遍历
	 */
	private void ergodicLrd(TreeNode node) {
		if(node.getLeftChild() != null) {
			ergodicLrd(node.getLeftChild());
		}
		
		if(node.getRightChild() != null) {
			ergodicLrd(node.getRightChild());
		}
		
		ergodicAction(node);
	}
	
	/**
	 * 前序线索化
	 */
	private void threadedDlr(TreeNode node) {
		if(node.getLeftChild() == null) {
			/**
			 * 将前序线索化的第一个节点的左子节点指向head节点
			 * 虽然前序遍历的第一个节点一定是根节点,但根节点有可能没有左子树
			 * 这样的话,其左子结点也可以指向headNode
			 */
			if(preNode == null) {
				node.setLeftChild(headNode);
			} else {
				node.setLeftChild(preNode);
			}
			node.setLeftThreadFlag(true);
		}
		
		if(preNode != null && preNode.getRightChild() == null) {
			preNode.setRightChild(node);
			preNode.setRightThreadFlag(true);
		}
		preNode = node;
		
		if(node.getLeftChild() != null) {
			threadedDlr(node.getLeftChild());
		}
		
		if(node.getRightChild() != null) {
			threadedDlr(node.getRightChild());
		}
	}
	
	/**
	 * 中序线索化
	 */
	private void threadeLdr(TreeNode node) {
		if(node.getLeftChild() != null) {
			threadeLdr(node.getLeftChild());
		}

		if(node.getLeftChild() == null) {
			if(preNode == null) {
				//将中序线索化的第一个节点的左子节点指向head节点
				node.setLeftChild(headNode);
			} else {
				node.setLeftChild(preNode);
			}
			node.setLeftThreadFlag(true);
		}
		if(preNode != null && preNode.getRightChild() == null) {
			preNode.setRightChild(node);
			preNode.setRightThreadFlag(true);
		}
		preNode = node;

		if(node.getRightChild() != null) {
			threadeLdr(node.getRightChild());
		}
	}
	
	/**
	 * 后序线索化,同时设置父节点,使二叉链表变成三叉链表
	 */
	private void threadeLrd(TreeNode node) {
		if(!node.getLeftThreadFlag() && node.getLeftChild() != null) {
			//设置父节点,后序遍历用到
			node.getLeftChild().setParent(node);
			threadeLrd(node.getLeftChild());
		}
		
		if(!node.getRightThreadFlag() && node.getRightChild() != null) {
			//设置父节点,后序遍历用到
			node.getRightChild().setParent(node);
			threadeLrd(node.getRightChild());
		}
		
		if(node.getLeftChild() == null) {
			if(preNode == null) {
				//将后序线索化的第一个节点的左子节点指向head节点
				node.setLeftChild(headNode);
			} else {
				node.setLeftChild(preNode);
			}
			node.setLeftThreadFlag(true);
		}
		if(preNode != null && preNode.getRightChild() == null) {
			preNode.setRightChild(node);
			preNode.setRightThreadFlag(true);
		}
		preNode = node;
	}
	
	/**
	 * 前序遍历线索二叉树
	 */
	private void ergodicThreadedDlr() {
		TreeNode node = rootNode;
		while(node != null && node != headNode) {
			ergodicAction(node);
			
			if(!node.getLeftThreadFlag()) {
				node = node.getLeftChild();
			} else {
				node = node.getRightChild(true);
			}
		}
	}
	
	/**
	 * 中序遍历线索二叉树
	 */
	private void ergodicThreadedLdr() {
		TreeNode node = rootNode;
		while(node != null && node != headNode) {
			while(!node.getLeftThreadFlag()) {
				node = node.getLeftChild();
			}
			
			ergodicAction(node);
			
			while(node.getRightThreadFlag() && node.getRightChild(true) != headNode) {
				node = node.getRightChild(true);
				ergodicAction(node);
			}
			
			node = node.getRightChild();
		}
	}
	
	/**
	 * 后续遍历线索二叉树
	 */
	private void ergodicThreadedLrd() {
		TreeNode node = rootNode;
		preNode = null;
		
		while(node != null) {
			/**
			 * 先找到最左边的子节点
			 * [node.getLeftChild() != preNode] 这个条件很重要
			 * 如果不加这个条件,那么通过[node = node.getParent();]返回到父节点之后,会继续在其左子树中循环
			 */
			while(node.getLeftChild() != null && !node.getLeftThreadFlag() && node.getLeftChild() != preNode) {
				node = node.getLeftChild();
			}
			
			/**
			 * 如果最左边的子节点有右子节点,那么这段跳过
			 * 如果最左边的子节点没有右子节点,那么一定有后继节点,则跟着后继节点一直遍历
			 */
			while(node != null && node.getRightThreadFlag()) {
				ergodicAction(node);
				preNode = node;
				node = node.getRightChild(true);
			}
			
			/**
			 * 后序遍历的最后一个节点一定是根节点
			 */
			if(node == rootNode) {
				ergodicAction(node);
				return;
			}
			
			/**
			 * 如果当前节点是从右子节点返回的
			 * 说明当前节点的所有子节点都遍历完了
			 * 那么就遍历当前节点,并返回到其父节点
			 */
			while(node != null && node.getRightChild() == preNode) {
				ergodicAction(node);
				preNode = node;
				node = node.getParent();
			}
			
			if(node != null && !node.getRightThreadFlag()) {
				node = node.getRightChild();
			}
		}
	}
	
	/**
	 * 前序查找
	 */
	private TreeNode searchDlr(TreeNode node, Object data) {
		if(node.getData().equals(data)) {
			return node;
		}
		
		if(node.getLeftChild() != null) {
			TreeNode result = searchDlr(node.getLeftChild(), data);
			if(result != null) {
				return result;
			}
		}
		
		if(node.getRightChild() != null) {
			TreeNode result = searchDlr(node.getRightChild(), data);
			if(result != null) {
				return result;
			}
		}

		return null;
	}
	
	/**
	 * 中序查找
	 */
	private TreeNode searchLdr(TreeNode node, Object data) {
		if(node.getLeftChild() != null) {
			TreeNode result = searchLdr(node.getLeftChild(), data);
			if(result != null) {
				return result;
			}
		}
		
		if(node.getData().equals(data)) {
			return node;
		}
		
		if(node.getRightChild() != null) {
			TreeNode result = searchLdr(node.getRightChild(), data);
			if(result != null) {
				return result;
			}
		}
		
		return null;
	}
	
	/**
	 * 后序查找
	 */
	private TreeNode searchLrd(TreeNode node, Object data) {
		if(node.getLeftChild() != null) {
			TreeNode result = searchLrd(node.getLeftChild(), data);
			if(result != null) {
				return result;
			}
		}
		
		if(node.getRightChild() != null) {
			TreeNode result = searchLrd(node.getRightChild(), data);
			if(result != null) {
				return result;
			}
		}
		
		if(node.getData().equals(data)) {
			return node;
		}
		
		return null;
	}
	
	/**
	 * 删除节点,连同以该节点为根节点的整个子树都删除
	 * @param searchRootNode	把该节点作为根节点在其所有子节点中搜索要删除的节点
	 * @param delNode			要删除的节点
	 * @return					是否删除成功
	 */
	private boolean deleteNode(TreeNode searchRootNode, TreeNode delNode) {
		if(searchRootNode.getLeftChild() == delNode) {
			searchRootNode.setLeftChild(null);
			return true;
		}
		if(searchRootNode.getRightChild() == delNode) {
			searchRootNode.setRightChild(null);
			return true;
		}
		
		if(searchRootNode.getLeftChild() != null) {
			if(deleteNode(searchRootNode.getLeftChild(), delNode)) {
				return true;
			}
		}
		if(searchRootNode.getRightChild() != null) {
			if(deleteNode(searchRootNode.getRightChild(), delNode)) {
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * 打印树(从上到下,一层一层打印)
	 * 
	 * "[]": 包含某个节点的左子结点和右子节点
	 * "|": 分隔一组左子结点和右子节点
	 * "○": 表示该位置为空
	 * "=": 分隔一组左右子节点和另一组兄弟左右子节点的符号
	 * 
	 * @param preLeverNodes		上层的所有节点的集合
	 */
	private void printTree(List<TreeNode> preLeverNodes, StringBuilder sb) {
		List<TreeNode> nodes = MyCollectionUtils.newList(TreeNode.class);
		
		for(int i = 0; i < preLeverNodes.size(); i++) {
			TreeNode node = preLeverNodes.get(i);
			
			sb.append("[");
			
			if(node.getLeftChild() != null && !node.getLeftThreadFlag()) {
				nodes.add(node.getLeftChild());
				sb.append(node.getLeftChild().printNode());
			} else {
				sb.append("○");
			}
			
			sb.append("|");
			
			if(node.getRightChild() != null && !node.getRightThreadFlag()) {
				nodes.add(node.getRightChild());
				sb.append(node.getRightChild().printNode());
			} else {
				sb.append("○");
			}
			
			sb.append("]");
			
			if(i != preLeverNodes.size() - 1) {
				sb.append("=");
			}
		}
		
		if(nodes.size() > 0) {
			sb.append("\n");
			printTree(nodes, sb);
		}
	}
	
}
