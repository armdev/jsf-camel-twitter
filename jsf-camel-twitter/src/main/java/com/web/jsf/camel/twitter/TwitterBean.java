package com.web.jsf.camel.twitter;

import java.io.Serializable;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

//@ManagedBean(name = "twitterBean")
//@SessionScoped
public class TwitterBean implements Serializable {

   //;

    private TwitterSearchBean appManager = null;

    public TwitterBean() {
       // System.out.println("TwitterBean: constructor called ");

    }

    public String receiveTwitterMessage(String message) {
        //System.out.println("Receive message:#### " + message);
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        appManager = context.getApplication().evaluateExpressionGet(
                context, "#{twitterSearchBean}", TwitterSearchBean.class);
      //  System.out.println("appManager### " + appManager);
        appManager.getMessageList().add(0,"Search Key: "+appManager.getKeyword()+ " | " + message);

        //System.out.println("appManager.getKeyword()#### " + appManager.getKeyword());
        return null;

    }

}
