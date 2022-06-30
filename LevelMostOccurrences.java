import java.util.ArrayDeque;

public class LevelMostOccurrences {
    /**
     * this function gets a number and the head of a binary tree and returns the number of the binary tree level
     * with the most occurrences of the given number, if there are no occurrences at all it returns -1
     * @param node the head of the Binary tree
     * @param num the number to look for in each level
     * @return the level with the most occurrences as int
     */
    public static int getLevelWithMostOccurrences(BinNode<Integer> node, int num) {
        //first ArrayDeque
        ArrayDeque<BinNode> arrayDeque = new ArrayDeque<>();
        //second ArrayDeque where all the children are entered
        ArrayDeque<BinNode> childrenArrayDeque = new ArrayDeque<>();
        if (node == null) return -1;
        int levelToReturn = -1;
        int occurrencesInLevel = 0;
        int maxOfAllLevels=-1;
        int currentLevel = 0;
        arrayDeque.add(node);
        // while there are still more level to count
        while (!arrayDeque.isEmpty()) {
            currentLevel++;
            //empty the arrayDeque and enter the children of each node to the children arrayDeque
            while (!arrayDeque.isEmpty()) {
                if (arrayDeque.peek().getLeft() != null)
                    childrenArrayDeque.add(arrayDeque.peek().getLeft());
                if (arrayDeque.peek().getRight() != null)
                    childrenArrayDeque.add(arrayDeque.peek().getRight());
                arrayDeque.pop();
            }
            //move the children arrayDeque to the first arrayDeque
            while (!childrenArrayDeque.isEmpty()) {
                BinNode<Integer> currentNode = childrenArrayDeque.pop();
                if (currentNode.getData() == num) {
                    occurrencesInLevel++;
                }
                arrayDeque.add(currentNode);
            }
            // if current level had more occurrences of the number than any other level, save it in a variable
            if (occurrencesInLevel > maxOfAllLevels && occurrencesInLevel>0) {
                levelToReturn = currentLevel;
                maxOfAllLevels = occurrencesInLevel;
            }
            occurrencesInLevel=0;
        }
        return levelToReturn;
    }
}
