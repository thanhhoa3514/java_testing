package mockitoTest.lesson1;

public class User {
        private String id;
        private String passwordHash;//pass da duoc ma hoa
        private boolean enabled;//Status

        public User(String id, String passwordHash, boolean enabled) {
            this.id = id;
            this.passwordHash = passwordHash;
            this.enabled = enabled;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPasswordHash() {
            return passwordHash;
        }

        public void setPasswordHash(String passwordHash) {
            this.passwordHash = passwordHash;
        }

        public boolean isEnabled() {
            return enabled;
        }

        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }

}
