/*!
 * This program is free software; you can redistribute it and/or modify it under the
 * terms of the GNU Lesser General Public License, version 2.1 as published by the Free Software
 * Foundation.
 *
 * You should have received a copy of the GNU Lesser General Public License along with this
 * program; if not, you can obtain a copy at http://www.gnu.org/licenses/old-licenses/lgpl-2.1.html
 * or from the Free Software Foundation, Inc.,
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Lesser General Public License for more details.
 *
 * Copyright (c) 2002-2013 Pentaho Corporation..  All rights reserved.
 */

package org.pentaho.ui.xul.test.swing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.awt.GraphicsEnvironment;

import javax.swing.JCheckBox;

import org.dom4j.Document;
import org.junit.After;
import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;
import org.pentaho.ui.xul.XulDomContainer;
import org.pentaho.ui.xul.XulRunner;
import org.pentaho.ui.xul.components.XulCheckbox;
import org.pentaho.ui.xul.swing.SwingXulLoader;
import org.pentaho.ui.xul.swing.SwingXulRunner;

public class SwingCheckboxTest {

  XulRunner runner = null;
  Document doc = null;
  XulDomContainer container;
  XulCheckbox check;
  XulCheckbox check2;

  @Before
  public void setUp() throws Exception {

    // Do not run on headless environment
    Assume.assumeTrue( !GraphicsEnvironment.isHeadless() );

    container = new SwingXulLoader().loadXul( "resource/documents/allTags.xul" );

    runner = new SwingXulRunner();
    runner.addContainer( container );
    check = (XulCheckbox) container.getDocumentRoot().getElementById( "checkbox" );
    check2 = (XulCheckbox) container.getDocumentRoot().getElementById( "checkbox2" );

  }

  @After
  public void tearDown() throws Exception {
    if ( runner != null ) {
      runner.stop();
    }
  }

  @Test
  public final void testLabel() {
    assertEquals( "test", check.getLabel() );
  }

  @Test
  public final void testCommand() {
    assertEquals( "foo.test()", check.getCommand() );
  }

  @Test
  public final void testIsChecked() {
    assertTrue( check.isChecked() );
  }

  @Test
  public final void testSetChecked() {
    check2.setChecked( false );
    assertTrue( !check2.isChecked() );
    check2.setChecked( true );
    assertTrue( check2.isChecked() );

  }

  @Test
  public final void testIsDisabled() {
    assertTrue( !check.isDisabled() );
  }

  @Test
  public final void testSetDisabled() {
    check.setDisabled( true );
    assertTrue( check.isDisabled() );
    check.setDisabled( false );
    assertTrue( !check.isDisabled() );
  }

  @Test
  public final void setManagedObject() {
    assertTrue( check.getManagedObject() instanceof JCheckBox );
  }

}
