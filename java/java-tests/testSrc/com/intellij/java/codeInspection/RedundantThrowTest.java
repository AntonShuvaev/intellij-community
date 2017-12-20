/*
 * Copyright 2000-2017 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
 */
package com.intellij.java.codeInspection;

import com.intellij.JavaTestUtil;
import com.intellij.codeInspection.unneededThrows.RedundantThrowsDeclarationInspection;
import com.intellij.testFramework.InspectionTestCase;

public class RedundantThrowTest extends InspectionTestCase {
  @Override
  protected String getTestDataPath() {
    return JavaTestUtil.getJavaTestDataPath() + "/inspection";
  }

  private void doTest() {
    doTest(false);
  }

  private void doTest(boolean checkRange) {
    final RedundantThrowsDeclarationInspection tool = new RedundantThrowsDeclarationInspection();
    doTest("redundantThrow/" + getTestName(false), tool, checkRange);
  }

  public void testSCR8322() { doTest(); }

  public void testSCR6858() { doTest(); }
  public void testFieldThrows() { doTest(); }

  public void testSCR6858ByRange() { doTest(true); }

  public void testSCR14543() { doTest(); }

  public void testRemote() { doTest(); }

  public void testEntryPoint() {
    final RedundantThrowsDeclarationInspection tool = new RedundantThrowsDeclarationInspection();
    tool.IGNORE_ENTRY_POINTS = true;
    doTest("redundantThrow/" + getTestName(true), tool, false, true);
  }

  public void testInherited() {
    doTest();
  }

  public void testImplicitSuper() {
    doTest();
  }

  public void testSelfCall() {
    doTest();
  }

  public void testThrownClausesInFunctionalExpressions() {
    doTest();
  }

  public void testThrownClausesInMethodReference() {
    doTest();
  }
}
