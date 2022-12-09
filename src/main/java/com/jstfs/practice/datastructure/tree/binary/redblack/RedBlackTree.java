package com.jstfs.practice.datastructure.tree.binary.redblack;

import java.util.Arrays;

import com.jstfs.common.utils.MyArrayUtils;
import com.jstfs.common.utils.MyRandomUtils;
import com.jstfs.practice.datastructure.tree.binary.TreeNode;
import com.jstfs.practice.datastructure.tree.binary.bst.BinarySortTree;

/**
 * 红黑树
 * 
 * 		也是一种优化的二叉排序树,但其左右子树的高度差是有可能大于1的,所以红黑树不是严格意义上的AVL
 * 		但对其进行平衡的代价较低,所以平均性能要强于AVL
 * 
 * 规则:
 * 		1, 每个节点都有颜色标识,不是红色就是黑色
 * 		2, 根节点必须是黑色
 * 		3, 红色节点的子节点必须是黑色(所以红色节点不能相连)
 * 		4, 所有空子节点都是黑色(比如只有一个子节点的节点,其另一个空着的子节点可以认为是黑色的,以及叶子节点,也可以认为其有两个黑色的空子节点)
 * 		5, 任意节点到其每个叶子节点的路径上都包含相同的黑色节点
 * 
 * @createBy	落叶
 * @createTime	2022年11月13日 下午8:29:09
 */
public class RedBlackTree extends BinarySortTree {
	private static int size = 10;
	private static int[] dataAry; 
	
	public static void main(String[] args) {
		MyRandomUtils.setSeed(System.currentTimeMillis());
		dataAry = MyRandomUtils.generateIntAry(size, 1, 4 * size);
		dataAry = new int[] {10, 11, 6, 15, 8, 2, 3, 12, 19, 14, 7, 1, 4, 5, 18, 13};

		System.out.println("原数组:\t\t" + Arrays.toString(dataAry));
		
		RedBlackTree rbTree = new RedBlackTree();
		rbTree.build(MyArrayUtils.toObjectAry(dataAry));
		rbTree.printTree();
		
		rbTree.deleteNode(3);
		System.out.println("==============删除3===============");
		rbTree.printTree();
	}
	
	/**
	 * 构建一棵红黑树
	 */
	@Override
	public void build(Object[] dataAry) {
		for(int i = 0; i < dataAry.length; i++) {
			addNode(new RBTreeNode(dataAry[i]));
		}
	}
	
	/**
	 * 添加节点成功后的动作
	 * 
	 * 红黑树添加完节点后需要判断是否满足红黑树规则,如果不满足则进行对应的操作
	 */
	@Override
	public void afterAddNode(TreeNode newNode) {
		RBTreeNode currNode = (RBTreeNode)newNode;
		RBTreeNode parentNode = (RBTreeNode)currNode.getParent();
		
		if(parentNode == null) {
			//如果插入的是根节点,则把颜色改为黑色
			System.out.println("===[插入的是根节点],开始调整颜色...");
			currNode.setRedFlag(false);
			System.out.println("===完成颜色调整,打印树...");
			printTree();
		} else if(parentNode.getRedFlag()) {
			//父节点是红色
			RBTreeNode grandpaNode = (RBTreeNode)parentNode.getParent();
			RBTreeNode uncleNode = null;
			if(grandpaNode != null) {
				if(grandpaNode.getLeftChild() == parentNode) {
					uncleNode = (RBTreeNode)grandpaNode.getRightChild();
				} else {
					uncleNode = (RBTreeNode)grandpaNode.getLeftChild();
				}
			}
			
			if(uncleNode != null && uncleNode.getRedFlag()) {
				/**
				 * 叔节点是红色,则:
				 * 		将爷节点变红色,
				 * 		父节点和叔节点变黑色,
				 * 		然后对爷节点重新判断(递归)
				 */
				System.out.println("===[父红叔红],开始调整颜色...");
				grandpaNode.setRedFlag(true);
				parentNode.setRedFlag(false);
				uncleNode.setRedFlag(false);
				System.out.println("===完成颜色调整,打印树...");
				printTree();
				
				System.out.println("===重新对 " + grandpaNode + " 进行调整...");
				afterAddNode(grandpaNode);
				
				System.out.println("[父红叔红],调整完,打印树...");
				printTree();
			} else {
				/**
				 * 叔节点是黑色(如果叔节点为null,也可以认为是黑色)
				 */
				if(parentNode.getLeftChild() == currNode) {
					//插入节点是左子结点
					if(grandpaNode.getLeftChild() == parentNode) {
						/**
						 * 父节点是左子节点,则
						 * 		父节点变黑色
						 * 		爷节点变红色
						 * 		以爷节点和父节点作为支点右旋
						 * 		对当前节点重新判断(递归)
						 */
						System.out.println("===[父红叔黑,父左自左],开始调整颜色...");
						parentNode.setRedFlag(false);
						grandpaNode.setRedFlag(true);
						System.out.println("===完成颜色调整,打印树...");
						printTree();
						
						rightRotate(grandpaNode);
						System.out.println("===完成右旋,打印树...");
						printTree();
						
						System.out.println("===重新对 " + currNode + " 进行调整...");
						afterAddNode(currNode);
						
						System.out.println("[父红叔黑,父左自左],调整完,打印树...");
						printTree();
					} else {
						/**
						 * 父节点是右子节点,则:
						 * 		以父节点和插入节点作为支点右旋
						 * 		对父节点重新判断(递归)
						 */
						System.out.println("===[父红叔黑,父右自左],开始调整...");
						
						rightRotate(parentNode);
						System.out.println("===完成右旋,打印树...");
						printTree();
						
						System.out.println("===重新对 " + parentNode + " 进行调整...");
						afterAddNode(parentNode);
						
						System.out.println("[父红叔黑,父右自左],调整完,打印树...");
						printTree();
					}
				} else {
					//插入节点是右子结点
					if(grandpaNode.getRightChild() == parentNode) {
						/**
						 * 父节点是右子节点,则
						 * 		父节点变黑色
						 * 		爷节点变红色
						 * 		以爷节点和父节点作为支点左旋
						 * 		对当前节点重新判断(递归)
						 */
						System.out.println("===[父红叔黑,父右自右],开始调整颜色...");
						parentNode.setRedFlag(false);
						grandpaNode.setRedFlag(true);
						System.out.println("===完成颜色调整,打印树...");
						printTree();
						
						leftRotate(grandpaNode);
						System.out.println("===完成左旋,打印树...");
						printTree();

						System.out.println("===重新对 " + currNode + " 进行调整...");
						afterAddNode(currNode);
						
						System.out.println("[父红叔黑,父右自右],调整完,打印树...");
						printTree();
					} else {
						/**
						 * 父节点是左子节点,则:
						 * 		以父节点和插入节点作为支点左旋
						 * 		对父节点重新判断(递归)
						 */
						System.out.println("===[父红叔黑,父左自右],开始调整...");
						
						leftRotate(parentNode);
						System.out.println("===完成左旋,打印树...");
						printTree();
						
						System.out.println("===重新对 " + parentNode + " 进行调整...");
						afterAddNode(parentNode);
						
						System.out.println("[父红叔黑,父左自右],调整完,打印树...");
						printTree();
					}
				}
			}
		} else {
			//父节点是黑色,什么都不做
			System.out.println("===[父节点是黑色],什么都不做");
		}
	}
	
	/**
	 * 删除数据
	 * 
	 * @param data	要删除的数据
	 * @return		是否成功删除
	 */
	@Override
	public boolean deleteNode(Object data) {
		TreeNode rootNode = getRootNode();
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
	 * @param delNode	要删除的数据
	 * @return			是否成功删除
	 */
	@Override
	public boolean deleteNode(TreeNode node) {
		RBTreeNode rootNode = (RBTreeNode)getRootNode();
		if(rootNode == null) {
			System.out.println("空树不能删除");
			return false;
		}
		
		RBTreeNode delNode = (RBTreeNode)node;
		if(rootNode == delNode) {
			System.out.println("删除根节点");
			setRootNode(null);
			return true;
		}
		
		RBTreeNode parentNode = (RBTreeNode)delNode.getParent();
		if(delNode.getLeftChild() == null && delNode.getRightChild() == null) {
			/**
			 * <1> 如果删除的节点是叶子节点
			 */
			if(delNode.getRedFlag()) {
				/**
				 * <1.1> 如果删除的节点是红色,则:
				 * 
				 * 		直接删除
				 */
				deleteAndUpdate(parentNode, delNode, null);
				return true;
			} else {
				/**
				 * <1.2> 如果删除的节点是黑色
				 * 
				 * 		这种情况最为复杂,需要分多种情况进行调整
				 * 		调整完之后再删除要删除的节点
				 */
				adjust(delNode);
				deleteAndUpdate(parentNode, delNode, null);
				return true;
			}
		} else if(delNode.getLeftChild() != null && delNode.getRightChild() != null) {
			/**
			 * <3> 如果删除的节点有两个子节点
			 * 
			 * 		此时删除的节点的颜色不需要关注
			 * 		先找到按照中序遍历的前驱节点或者后继节点,任选其一
			 * 		将前驱节点或者后继节点的数据覆盖到要删除的节点上
			 * 		删除前驱节点或者后继节点(递归)
			 */
			
			/**
			 * 使用后继节点
			RBTreeNode nextNodeForLDR = (RBTreeNode)delNode.getRightChild();
			while(true) {
				if(nextNodeForLDR.getLeftChild() != null) {
					nextNodeForLDR = (RBTreeNode)nextNodeForLDR.getLeftChild();
				} else {
					break;
				}
			}
			
			delNode.setData(nextNodeForLDR.getData());
			return deleteNode(nextNodeForLDR);
			*/
			
			/**
			 * 使用前驱节点
			 */
			RBTreeNode preNodeForLDR = (RBTreeNode)delNode.getLeftChild();
			while(true) {
				if(preNodeForLDR.getRightChild() != null) {
					preNodeForLDR = (RBTreeNode)preNodeForLDR.getRightChild();
				} else {
					break;
				}
			}
			delNode.setData(preNodeForLDR.getData());
			return deleteNode(preNodeForLDR);
		} else {
			/**
			 * <2> 如果删除的节点只有一个子节点
			 * 
			 * 		这种情况下,删除的节点一定是黑色,同时其唯一的那个子节点一定是红色,则:
			 * 		删除该节点
			 * 		让其唯一的子节点代替自己的位置
			 * 		将原本为红色的子节点变为黑色
			 */
			RBTreeNode onlyOneChildNode = (RBTreeNode)(delNode.getLeftChild() != null ? delNode.getLeftChild() : delNode.getRightChild());
			deleteAndUpdate(parentNode, delNode, onlyOneChildNode);
			onlyOneChildNode.setRedFlag(false);
			return true;
		}
	}

//======================================= public/private 方法分界线 TODO =========================================================================
	
	/**
	 * 调整删除黑色叶子节点时的情况
	 * 
	 * 此时,要删除的节点一定存在兄节点,否则违反规则5
	 * 
	 * @param delNode	删除的节点
	 */
	private void adjust(RBTreeNode delNode) {
		RBTreeNode parentNode = (RBTreeNode)delNode.getParent();
		if(parentNode == null) {
			//说明递归到了根节点
			return;
		}
		
		RBTreeNode brotherNode = null;
		boolean parentBrotherLeftFlag = false;		//要删除节点是否是其父节点的左子结点
		
		if(parentNode.getLeftChild() == delNode) {
			brotherNode = (RBTreeNode)parentNode.getRightChild();
		} else {
			brotherNode = (RBTreeNode)parentNode.getLeftChild();
			parentBrotherLeftFlag = true;
		}
		
		if(!brotherNode.getRedFlag()) {
			/**
			 * <1.2.1> 如果兄节点是黑色
			 */
			RBTreeNode brotherLeftChild = (RBTreeNode)brotherNode.getLeftChild();
			RBTreeNode brotherRightChild = (RBTreeNode)brotherNode.getRightChild();
			boolean brotherLeftRed = false;
			boolean brotherRightRed = false;
			if(brotherLeftChild != null && brotherLeftChild.getRedFlag()) {
				brotherLeftRed = true;
			}
			if(brotherRightChild != null && brotherRightChild.getRedFlag()) {
				brotherRightRed = true;
			}
			
			if(brotherLeftRed || brotherRightRed) {
				/**
				 * 兄节点最少有一个红色子节点
				 */
				if(parentBrotherLeftFlag) {
					/**
					 * 兄节点是左子结点
					 */
					if(brotherLeftRed) {
						/**
						 * <1.2.1.1> 如果兄节点有一个同向的红色子节点,并且是同左
						 * 
						 * 		以父节点和兄节点作为支点右旋
						 * 		父节点和兄节点交换颜色
						 * 		兄的同向红色子节点变黑色
						 */
						rightRotate(parentNode);
						brotherNode.setRedFlag(parentNode.getRedFlag());
						parentNode.setRedFlag(false);
						brotherLeftChild.setRedFlag(false);
					} else if(brotherRightRed) {
						/**
						 * <1.2.1.4> 如果兄节点有一个反向的红色子节点,并且是兄左和兄右子
						 * 
						 * 		以兄节点和其右子结点作为支点左旋
						 * 		兄节点和兄的右子结点交换颜色
						 * 		然后就变成了<1.2.1.1>,递归即可
						 */
						leftRotate(brotherNode);
						brotherRightChild.setRedFlag(false);
						brotherNode.setRedFlag(true);
						adjust(delNode);
					}
				} else {
					/**
					 * 兄节点是右子结点
					 */
					if(brotherRightRed) {
						/**
						 * <1.2.1.2> 如果兄节点有一个同向的红色子节点,并且是同右
						 * 
						 * 		以父节点和兄节点作为支点左旋
						 * 		父节点和兄节点交换颜色
						 * 		兄的同向红色子节点变黑色
						 */
						leftRotate(parentNode);
						brotherNode.setRedFlag(parentNode.getRedFlag());
						parentNode.setRedFlag(false);
						brotherRightChild.setRedFlag(false);
					} else if(brotherLeftRed) {
						/**
						 * <1.2.1.3> 如果兄节点有一个反向的红色子节点,并且是兄右和兄左子
						 * 
						 * 		以兄节点和其左子结点作为支点右旋
						 * 		兄节点和兄的左子结点交换颜色
						 * 		然后就变成了<1.2.1.2>,递归即可
						 */
						rightRotate(brotherNode);
						brotherNode.setRedFlag(true);
						brotherLeftChild.setRedFlag(false);
						adjust(delNode);
					}
				}
			} else {
				/**
				 * <1.2.1.5> 如果兄节点无红色子节点
				 */
				if(parentNode.getRedFlag()) {
					/**
					 * <1.2.1.5.1> 如果父节点是红色
					 * 
					 * 		父节点和兄节点交换颜色
					 */
					parentNode.setRedFlag(false);
					brotherNode.setRedFlag(true);
				} else {
					/**
					 * <1.2.1.5.2> 如果父节点是黑色
					 * 
					 * 		兄节点变红色
					 * 		对父节点进行递归(沿着父节点一直往上递归,直到碰到其他情况或者递归到根节点则递归结束)
					 */
					brotherNode.setRedFlag(true);
					System.out.println("<1.2.1.5.2> " + parentNode + " 开始递归...");
					adjust(parentNode);
				}
			}
		} else {
			/**
			 * <1.2.2> 如果兄节点是红色,那么父节点必为黑色
			 */
			if(parentBrotherLeftFlag) {
				/**
				 * <1.2.2.1> 如果兄节点是左子结点
				 * 
				 * 		以父节点和兄节点作为支点右旋
				 * 		兄节点和父节点交换颜色
				 * 		然后就变成了<1.2.1>,递归即可
				 */
				rightRotate(parentNode);
			} else {
				/**
				 * <1.2.2.2> 如果兄节点是右子节点
				 * 
				 * 		以父节点和兄节点作为支点左旋
				 * 		兄节点和父节点交换颜色
				 * 		然后就变成了<1.2.1>,递归即可
				 */
				leftRotate(parentNode);
			}
			
			parentNode.setRedFlag(true);
			brotherNode.setRedFlag(false);
			adjust(delNode);
		}
	}
}
