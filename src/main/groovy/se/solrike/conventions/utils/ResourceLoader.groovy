package se.solrike.conventions.utils

import org.gradle.api.Project

/**
 * @author Lucas Persson
 */
class ResourceLoader {

  public static String getResourceAsString(String name) {
    return getResource(name).text
  }

  /**
   *
   * @param name
   * @return  e.g. jar:file:/some-path/myjar.jar!/path-inside-jar/the-file.xml
   */
  public static URI getResourceAsUri(String name) {
    return getResource(name).toURI()
  }

  public static URL getResource(String name) {
    return ResourceLoader.class.getResource(name)
  }

  /**
   * load the the resource and return a file object since some APIs requires a file object.
   *
   * @param project
   * @param name
   * @return the file
   */
  public static File getResourceAsFile(Project project, String name) {
    File file = project.file(project.resources.text.fromUri(getResourceAsUri(name)))
    // change file file type ending from the generated .txt to what the resource has
    String fileType = name.substring(name.lastIndexOf('.'))
    String newFileName = file.absolutePath + fileType
    project.ant.move(file:file.absolutePath, toFile:newFileName)
    return project.file(newFileName)
  }
}
