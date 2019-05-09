package com.jstfs.practice.datastructure.chain.skiplist;

/**
 * 跳跃表(跳表):
 *		由于二分查找法依赖的是数组可根据下标随机访问的特性,那么如果数据存储在链表中,是否可以使用二分查找法?
 *		这就要说到跳表,它是对链表进行改造,在每间隔若干个数据之后提取一个数据到上一级,这一级就称作索引或索引层.
 *		在索引层上可以继续重复提取的动作,最终可以提取出多级索引,而最上面的一层索引最少存在2个数据,这种链表+多级索引的结构就叫做跳表.
 *
 *		查找某个数据的时候,先从最顶层索引开始,
 *
 *	时间复杂度: O((x+1)*logxN)	(x:表示每x个节点提取一个到上一个索引层)
 *
 *		x=2,那么一共有log₂N个索引层,在查找的时候,每一层最多遍历(x+1)=3个节点就可以向下一个索引层查找,所以精确的时间复杂度是O(3*log₂N),以此类推:
 *		x=3,	O(4*log₃N)
 *		x=4,	O(5*log₄N)
 *		......
 *		......
 *		得到时间复杂度就是: O((x+1)*logxN)
 *		
 *		使用底下的 testTimeComplexity()方法来模拟不同数量级的数据大小通过函数: y = (x+1)*(logN/logx)		(使用对数的换底公式)
 *		来计算 x 等于多少时,时间复杂度最低?
 *		结果是: 数量级从100到10000000时,都是 x=4 时,时间复杂度最低
 *		所以我此处的例子中会以每4个节点提取一个到上一个层索引.
 *		
 * @createBy jstfs
 * @createTime 2019-1-21 下午5:18:31
 */
public class SkipList<T extends Comparable<? super T>> {
//	private int indexLevel = 0;
//	private int x = 4;	//表示每x个节点提取一个到上一个索引层
	
	public static void main(String[] args) {
		
	}
	
	public static void testTimeComplexity() {
		getMinTimeComplexity(100);
		getMinTimeComplexity(1000);
		getMinTimeComplexity(10000);
		getMinTimeComplexity(100000);
		getMinTimeComplexity(1000000);
		getMinTimeComplexity(10000000);
	}
	
	private static int getMinTimeComplexity(int n) {
		double minY = 0;
		int minYx = 0;
		for(int x = 2; x < (n/2); x++) {
			double y = getTimeComplexity(n, x);
			if(minY == 0 || minY > y) {
				minY = y;
				minYx = x;
			}
		}
		System.out.printf("n=%-9d\tx=%-4d\tminY=%6.4f", n, minYx, minY);
		System.out.println();
		return minYx;
	}
	
	private static double getTimeComplexity(int n, int x) {
		double y = (x+1) * Math.log(n) / Math.log(x);
		return y;
	}

//    private int maxLevel;
//    private SkipListNode<T>[] root;
//    private int[] powers;
//    private Random rd = new Random();
//    SkipList() {
//        this(4);
//    }
//    SkipList(int i) {
//        maxLevel = i;
//        root = new SkipListNode[maxLevel];
//        powers = new int[maxLevel];
//        for (int j = 0; j < maxLevel; j++)
//            root[j] = null;
//        choosePowers();
//    }
//    public boolean isEmpty() {
//        return root[0] == null;
//    }
//    public void choosePowers() {
//        powers[maxLevel-1] = (2 << (maxLevel-1)) - 1;    // 2^maxLevel - 1
//        for (int i = maxLevel - 2, j = 0; i >= 0; i--, j++)
//           powers[i] = powers[i+1] - (2 << j);           // 2^(j+1)
//    }
//    public int chooseLevel() {
//        int i, r = Math.abs(rd.nextInt()) % powers[maxLevel-1] + 1;
//        for (i = 1; i < maxLevel; i++)
//            if (r < powers[i])
//                return i-1; // return a level < the highest level;
//        return i-1;         // return the highest level;
//    }
//    // make sure (with isEmpty()) that search() is called for a nonempty list;
//    public T search(T key) { 
//        int lvl;
//        SkipListNode<T> prev, curr;            // find the highest nonnull
//        for (lvl = maxLevel-1; lvl >= 0 && root[lvl] == null; lvl--); // level;
//        prev = curr = root[lvl];
//        while (true) {
//            if (key.equals(curr.key))          // success if equal;
//                 return curr.key;
//            else if (key.compareTo(curr.key) < 0) { // if smaller, go down,
//                 if (lvl == 0)                 // if possible
//                      return null;      
//                 else if (curr == root[lvl])   // by one level
//                      curr = root[--lvl];      // starting from the
//                 else curr = prev.next[--lvl]; // predecessor which
//            }                                  // can be the root;
//            else {                             // if greater,
//                 prev = curr;                  // go to the next
//                 if (curr.next[lvl] != null)   // non-null node
//                      curr = curr.next[lvl];   // on the same level
//                 else {                        // or to a list on a lower level;
//                      for (lvl--; lvl >= 0 && curr.next[lvl] == null; lvl--);
//                      if (lvl >= 0)
//                           curr = curr.next[lvl];
//                      else return null;
//                 }
//            }
//        }
//    }
//    public void insert(T key) {
//        SkipListNode<T>[] curr = new SkipListNode[maxLevel];
//        SkipListNode<T>[] prev = new SkipListNode[maxLevel];
//        SkipListNode<T> newNode;
//        int lvl, i;
//        curr[maxLevel-1] = root[maxLevel-1];
//        prev[maxLevel-1] = null;
//        for (lvl = maxLevel - 1; lvl >= 0; lvl--) {
//            while (curr[lvl] != null && curr[lvl].key.compareTo(key) < 0) { 
//                prev[lvl] = curr[lvl];           // go to the next
//                curr[lvl] = curr[lvl].next[lvl]; // if smaller;
//            }
//            if (curr[lvl] != null && key.equals(curr[lvl].key)) // don't 
//                return;                          // include duplicates;
//            if (lvl > 0)                         // go one level down
//                if (prev[lvl] == null) {         // if not the lowest
//                      curr[lvl-1] = root[lvl-1]; // level, using a link
//                      prev[lvl-1] = null;        // either from the root
//                }
//                else {                           // or from the predecessor;
//                     curr[lvl-1] = prev[lvl].next[lvl-1];
//                     prev[lvl-1] = prev[lvl];
//                }
//        }
//        lvl = chooseLevel();                // generate randomly level 
//        newNode = new SkipListNode<T>(key,lvl+1); // for newNode;
//        for (i = 0; i <= lvl; i++) {        // initialize next fields of
//            newNode.next[i] = curr[i];      // newNode and reset to newNode
//            if (prev[i] == null)            // either fields of the root
//                 root[i] = newNode;         // or next fields of newNode's
//            else prev[i].next[i] = newNode; // predecessors;
//        }
//    }
}