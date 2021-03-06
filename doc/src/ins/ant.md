Ant by itself does not include its dependency manager, named Ivy, so you need to enable it in your root project node. I 
also added a bonus target that does the ivy jar installation so you don't have to do it by hand. Ant and Ivy combo looks
quite long and complicated, but only because I left out everything not-dependency related from everything. Maven is more
verbose, trust me. Ant calls Maven's repositories, resolvers like Sbt as that took it from this.  
For clarity for newcomers all of the files start with their root node with non-related settings left out, rather than
having only snippets like for the other systems.

Enable Ivy:
```xml
<!--In your build.xml-->
<project xmlns:ivy="antlib:org.apache.ivy.ant">
    <!-- Ivy configuration and auto-install target -->
  <available classname="org.apache.ivy.Main" property="ivy.installed"/> 

  <target name="install-ivy" description="Install ivy" unless="ivy.installed">
    <mkdir dir="${user.home}/.ant/lib"/>
    <get dest="${user.home}/.ant/lib/ivy.jar" 
         src="central.maven.org/maven2/org/apache/ivy/ivy/2.5.0-rc1/ivy-2.5.0-rc1.jar"/>
    <fail message="Ivy has been installed. Run the build again"/>
  </target>
  
  <!-- Resolving dependencies from the ivy.xml file -->
  <target name="resolve" description="--> retrieve dependencies with Ivy">
    <ivy:retrieve/>
  </target>
  <!--project settings-->
</project>
```

Adding JitPack:
```xml
<!--In your ivysettings.xml-->
<ivysettings>
  <!-- Other settings -->
  <resolvers>
    <chain name="chain">
      <!-- Other resolvers -->
      <ibiblio name="jitpack" m2compatible="true" root="http://jitpack.io"/>
    </chain>
  </resolvers>
</ivysettings>
```

Adding the dependency:
```xml
<!--In your ivy.xml-->
<ivy-module version="2.0">
  <!-- Other settings -->
  <dependencies>
    <!-- Other dependencies -->
    <dependency org="com.github.isbodand" name="nhstatistics-core" rev="1.3.5"/>
  </dependencies>
</ivy-module>
```
