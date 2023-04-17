package se.solrike.conventions.utils

import org.gradle.api.Project

/**
 * Continuous Integration Utils
 */
class CiUtils {

  /**
   * @return build number or 'local' if not on Jenkins, Gitlab-CI, AWS CodeBuild
   */
  static String getBuildNumber()  {
    return System.getenv('BUILD_NUMBER') ?: (System.getenv('CI_PIPELINE_ID') ?: (System.getenv('CODEBUILD_BUILD_NUMBER') ?: 'local'))
  }

  /**
   * @return the branch name or empty string in case of main or a release branch
   */
  static String getBranchName(Project project) {
    String branchName = System.getenv('BRANCH_NAME') ?: project.grgit.branch.current().name
    return (branchName == 'main' || branchName.endsWith('_branch') ? '' : branchName.replace('/', '-') + '-')
  }
}
