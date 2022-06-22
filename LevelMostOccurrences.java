import java.util.ArrayDeque;

public class LevelMostOccurrences {
    public static int getLevelWithMostOccurrences(BinNode<Integer> node, int num) {
        ArrayDeque<BinNode> arrayDeque = new ArrayDeque<>();
        ArrayDeque<BinNode> childrenArrayDeque = new ArrayDeque<>();
        if (node == null) return -1;

        int size = 1;
        int levelToReturn = -1;
        int occurrencesInLevel = 0;
        int maxOfAllLevels=-1;
        int currentLevel = 0;
        arrayDeque.add(node);
        while (!arrayDeque.isEmpty()) {
            currentLevel++;
            while (!arrayDeque.isEmpty()) {
                if (arrayDeque.peek().getLeft() != null)
                    childrenArrayDeque.add(arrayDeque.peek().getLeft());
                if (arrayDeque.peek().getRight() != null)
                    childrenArrayDeque.add(arrayDeque.peek().getRight());
                arrayDeque.pop();
            }
            while (!childrenArrayDeque.isEmpty()) {
                BinNode<Integer> currentNode = childrenArrayDeque.pop();
                if (currentNode.getData() == num) {
                    occurrencesInLevel++;
                }
                arrayDeque.add(currentNode);
            }
            if (occurrencesInLevel > maxOfAllLevels && occurrencesInLevel>0) {
                levelToReturn = currentLevel;
                maxOfAllLevels = occurrencesInLevel;
            }
            occurrencesInLevel=0;
        }
        return levelToReturn;
    }
}
