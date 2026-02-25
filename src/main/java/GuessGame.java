public class GuessGame {
        private int pick;  // 真实答案

        public GuessGame(int pick) {
            this.pick = pick;
        }

        // 模拟系统 API
        public int guess(int num) {
            if (num == pick) return 0;
            else if (num > pick) return -1;
            else return 1;
        }
    }