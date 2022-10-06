package com.jstfs.practice.algorithm.sort.heap;

import java.util.Arrays;

import com.jstfs.common.utils.MyRandomUtils;

/**
 * 堆排序: 
 * 		1, 先将原数组转为二叉堆
 * 			如果要求升序,则转为大顶堆(以下步骤按升序为目的)
 * 			如果是降序,则转为小顶堆
 * 		2, 将数组的首元素与末尾元素交换
 * 			那么末尾元素就一定是最大的,此时可以看作是数组的长度在逻辑上减1
 * 			相当于末尾元素就排除在数组之外了
 * 		3, 由于第2步的交换,首元素变小了,剩下(的逻辑上)的数组元素就不满足二叉堆的规则了
 * 			所以需要将剩下(的逻辑上)的数组部分再次转为二叉堆
 * 			然后再重复第2步和第3步,直到逻辑上的数组就剩首元素一个元素时,就是有序的了
 * 
 * 特点:
 * 		1, 原地排序算法
 * 		2, 不稳定的排序算法
 * 		3, 时间复杂度:
 * 			最好时间复杂度: O(N*log₂N)
 * 			平均时间复杂度: O(N*log₂N)
 * 			最坏时间复杂度: O(N*log₂N)
 * 
 * @createBy 	落叶
 * @createTime 	2022年10月6日 上午2:41:51
 */
public class HeapSort {
	private static int size = 4000000;
	
	public static void main(String[] args) {
		HeapSort hs = new HeapSort();
		int[] ary = MyRandomUtils.generateIntAry(size, 1, 4 * size);
		System.out.println("原数组:\t" + Arrays.toString(ary));
		
//		System.out.println("开始时间:" + MyDateUtils.getNowStr(MyDateUtils.yyyyMMdd_HHmmssSSS_));
		hs.sort(ary, true);
//		System.out.println("结束时间:" + MyDateUtils.getNowStr(MyDateUtils.yyyyMMdd_HHmmssSSS_));
		System.out.println("排序后:\t" + Arrays.toString(ary));
	}
	
	/**
	 * @param ary	原数组
	 * @param flag	true-大顶堆,false-小顶堆
	 */
	public void sort(int[] ary, boolean flag) {
		int temp = 0;	//用于交换的临时变量
		
		if(flag) {
			for(int i = (ary.length / 2 -1); i >= 0; i--) {
				/**
				 * 从二叉堆的最后一个非叶子节点开始,从下往上,从右往左,依次将各子树调整为大顶堆
				 * (ary.length / 2 - 1) 就是二叉堆中最后一个非叶子节点的下标
				 */
				toBigTopHeap(ary, i, ary.length);
			}
			
			for(int j = ary.length -1; j > 0; j--) {
				/**
				 * 交换首尾两个元素
				 */
				temp = ary[j];
				ary[j] = ary[0];
				ary[0] = temp;
				
				/**
				 * 首元素变小了,就不满足二叉堆的规则了,所以需要继续将数组变为二叉堆
				 * 
				 * 下面的 toBigTopHeap()方法的第三个参数为什么传j?
				 * 		由于末尾元素已经是最大值了,已经排好序了,该数组从逻辑上看,个数应该减1
				 * 		由于j本来就开始于 (ary.length - 1),并且每次轮都进行j--
				 * 		所以j的值刚好就是本次要调整为二叉堆的部分的长度
				 * 
				 * 下面的 toBigTopHeap()方法的第二个参数为什么传0?
				 * 		当不考虑末尾元素的变化时,其实变化了的只有首元素
				 * 		所以每次只需要将以首元素为根节点的整个二叉堆来进行调整
				 */
				toBigTopHeap(ary, 0, j);
			}
		}
	}
	
	/**
	 * 将原数组的某一部分调整成大顶堆
	 * 
	 * @param ary		原数组
	 * @param i			需要调整的部分的根节点(将下标i对应的节点作为根节点,调整的就是该节点以及所有子节点形成的子树)
	 * @param length	本次调整的数组的最大长度(从下标i开始算起)
	 */
	private void toBigTopHeap(int[] ary, int i, int length) {
		int temp = ary[i];	//保存根节点的值,用语最后的交换
		
		/**
		 * (2 * i + 1) 是下标为i的节点的左子结点
		 * 也就是k从该子树的左子结点开始判断
		 * 由于每一轮循环后,一旦k的值发生变化了(即下标k的元素变化了)
		 * 那么需要再将k作为父节点,与其左右子节点再进行判断,
		 */
		for(int k = (2 * i + 1); k < length; k = (2 * k + 1)) {
			if(ary[k + 1] > ary[k] && k + 1 < length) {
				//右子节点大就将k移动到右子节点的下标
				k++;
			}
			if(ary[k] > temp) {
				//大元素上移到根节点
				ary[i] = ary[k];	//[***]
				//修改i的值,为下一轮比较做准备
				i = k;
			} else {
				break;
			}
		}
		
		/**
		 * 循环结束,将本次的根节点的值赋值给最后发生交换了的节点
		 * 这也是[***]这行代码只是将大元素ary[k]上移,但没有将被修改的ary[i]下移的原因
		 * 因为ary[k]还可能有子节点,就需要继续比较的原因,这样可以少一点赋值操作
		 */
		ary[i] = temp;
	}
}
