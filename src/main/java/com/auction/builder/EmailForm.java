package com.auction.builder;

/**
 * Builder for generate email form
 * <p>
 * Created by Helga on 30.11.2017.
 */
public class EmailForm {
    private String recipient;
    private String subject;
    private String message;

    private EmailForm() {
    }

    public EmailForm(String recipient, String subject, String message) {
        this.recipient = recipient;
        this.subject = subject;
        this.message = message;
    }

    public static Builder newBuilder() {
        return new EmailForm().new Builder();
    }

    public String getRecipient() {
        return recipient;
    }

    public String getSubject() {
        return subject;
    }

    public String getMessage() {
        return message;
    }

    public class Builder {
        private Builder() {
        }

        public Builder setRecipient(String recipient) {
            EmailForm.this.recipient = recipient;
            return this;
        }

        public Builder setSubject(String subject) {
            EmailForm.this.subject = subject;
            return this;
        }

        public Builder setMessage(String message) {
            EmailForm.this.message = message;
            return this;
        }

        public EmailForm build() {
            return EmailForm.this;
        }

    }
}
