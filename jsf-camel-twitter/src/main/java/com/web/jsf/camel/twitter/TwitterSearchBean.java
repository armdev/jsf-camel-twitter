package com.web.jsf.camel.twitter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

@ManagedBean(name = "twitterSearchBean")
@ApplicationScoped
public class TwitterSearchBean implements Serializable {

    private String keyword;
    private List<String> messageList = new ArrayList<>();

    public TwitterSearchBean() {
        messageList = new ArrayList<>();
    }

    public String doAction() throws Exception {

        CamelContext context = new DefaultCamelContext();
        try {
            context.addRoutes(new RouteBuilder() {
                @Override
                public void configure() throws Exception {
                    from("twitter://search?type=direct&keywords=" + keyword + "s&consumerKey=JNufxx4RQtG0QxYLnYZA&consumerSecret=oRcYOGoJclJYPLW9XkLjKEe5otpwSXQJqlNEuvxlg&accessToken=91971230-8Ty6L78ijg8S54KZLm5SeYFDkbLJcwAIvB4hfWGsc&accessTokenSecret=XVHetaxjg8snLpoRr4ycgYfUwBSz2F2HKZR34CI3b0TFn")
                            .to("stream:out").bean(TwitterBean.class, "receiveTwitterMessage(${body})");
                }
            });

            ProducerTemplate template = context.createProducerTemplate();
            context.start();

        } finally {
            context.stop();
        }

        //  return "messagelist";
        return null;

    }

    public List<String> getMessageList() {
        //System.out.println("messageList size " + messageList.size());
        return messageList;
    }

    public void setMessageList(List<String> messageList) {
        this.messageList = messageList;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

}
