package bluedot.electrochemistry.service.search;

/**
 * @author Senn
 * @create
 */
public enum SearchPage {
    ACCOUNT_PAGE(1),ALGORITHM_PAGE(2),FILE_PAGE(3),OPERATION_PAGE(4);
    int index;
    SearchPage(int index) {
            this.index = index;
    }

    public int getIndex() {
        return index;
    }
}
