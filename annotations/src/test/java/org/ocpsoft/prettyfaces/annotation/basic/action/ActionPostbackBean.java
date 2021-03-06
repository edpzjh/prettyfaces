package org.ocpsoft.prettyfaces.annotation.basic.action;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.ocpsoft.prettyfaces.annotation.ForwardTo;
import org.ocpsoft.prettyfaces.annotation.URLAction;
import org.ocpsoft.prettyfaces.annotation.URLPattern;

@ManagedBean
@RequestScoped
@URLPattern("/action")
@ForwardTo("/faces/action-postback.xhtml")
public class ActionPostbackBean
{

   private boolean actionOnPostbackDefault = false;
   private boolean actionOnPostbackTrue = false;
   private boolean actionOnPostbackFalse = false;

   @URLAction
   public void action1()
   {
      actionOnPostbackDefault = true;
   }

   @URLAction(onPostback = true)
   public void action2()
   {
      actionOnPostbackTrue = true;
   }

   @URLAction(onPostback = false)
   public void action3()
   {
      actionOnPostbackFalse = true;
   }

   public boolean isActionOnPostbackDefault()
   {
      return actionOnPostbackDefault;
   }

   public boolean isActionOnPostbackTrue()
   {
      return actionOnPostbackTrue;
   }

   public boolean isActionOnPostbackFalse()
   {
      return actionOnPostbackFalse;
   }

}
