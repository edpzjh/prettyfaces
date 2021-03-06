package org.ocpsoft.prettyfaces.test;

import java.io.File;

import org.jboss.shrinkwrap.api.GenericArchive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.ocpsoft.rewrite.test.RewriteTest;
import org.ocpsoft.rewrite.test.RewriteTestBase;

public class PrettyFacesTest extends RewriteTestBase
{
   public static WebArchive getBaseDeployment()
   {
      GenericArchive rewriteImplServlet = resolveDependency("org.ocpsoft.rewrite:rewrite-impl-servlet:jar:1.0.6-SNAPSHOT");
      GenericArchive rewriteImplAnnotations = resolveDependency("org.ocpsoft.rewrite:rewrite-config-annotations-impl:jar:1.0.6-SNAPSHOT");
      GenericArchive rewriteIntegrationFaces = resolveDependency("org.ocpsoft.rewrite:rewrite-integration-faces:jar:1.0.6-SNAPSHOT");

      WebArchive deployment = ShrinkWrap
               .create(WebArchive.class, "rewrite-test.war")

               /*
                * PrettyFaces
                */
               .addAsLibraries(getPrettyFacesArchive())

               /*
                * Rewrite
                */
               .addAsLibraries(rewriteImplServlet)
               .addAsLibraries(rewriteImplAnnotations)
               .addAsLibraries(rewriteIntegrationFaces)

               .addAsWebInfResource("faces-config.xml", "faces-config.xml");

      if(RewriteTest.isJetty())
      {
         /*
          * Set the JSF implementation
          */
         deployment.addAsLibraries(resolveDependencies("org.glassfish:javax.faces:jar:2.1.7"));
         deployment.setWebXML("jetty-web.xml");
      }
      
      return deployment;

   }

   protected static JavaArchive getPrettyFacesArchive()
   {
      return ShrinkWrap.create(JavaArchive.class, "prettyfaces.jar")
               .addAsResource(new File("../annotations/target/classes"));
   }

}
