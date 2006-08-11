//
// IFormatReader.java
//

/*
LOCI Bio-Formats package for reading and converting biological file formats.
Copyright (C) 2005-2006 Melissa Linkert, Curtis Rueden, Chris Allan
and Eric Kjellman.

This program is free software; you can redistribute it and/or modify
it under the terms of the GNU Library General Public License as published by
the Free Software Foundation; either version 2 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU Library General Public License for more details.

You should have received a copy of the GNU Library General Public License
along with this program; if not, write to the Free Software
Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
*/

package loci.formats;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Hashtable;




/** Abstract superclass of all biological file format readers. */
public interface IFormatReader extends IFormatHandler {
  /** Checks if the given block is a valid header for this file format. */
  public boolean isThisType(byte[] block);

  /** Determines the number of images in the given file. */
  public int getImageCount(String id)
    throws FormatException, IOException;

  /** Checks if the images in the file are RGB. */
  public boolean isRGB(String id)
    throws FormatException, IOException;

  /** Get the size of the X dimension. */
  public int getSizeX(String id) throws FormatException, IOException;

  /** Get the size of the Y dimension. */
  public int getSizeY(String id) throws FormatException, IOException;

  /** Get the size of the Z dimension. */
  public int getSizeZ(String id) throws FormatException, IOException;

  /** Get the size of the C dimension. */
  public int getSizeC(String id) throws FormatException, IOException;

  /** Get the size of the T dimension. */
  public int getSizeT(String id) throws FormatException, IOException;

  /** Return true if the data is in little-endian format. */
  public boolean isLittleEndian(String id)
    throws FormatException, IOException;

  /**
   * Return a five-character string representing the dimension order
   * within the file.
   */
  public String getDimensionOrder(String id)
    throws FormatException, IOException;

  /** Obtains the specified image from the given file. */
  public BufferedImage openImage(String id, int no)
    throws FormatException, IOException;

  /**
   * Obtains the specified image from the given file as a byte array.
   */
  public byte[] openBytes(String id, int no)
    throws FormatException, IOException;

  /** Closes the currently open file. */
  public void close() throws FormatException, IOException;


  /**
   * Opens an existing file from the given filename.
   *
   * @return Java Images containing pixel data
   */
  public BufferedImage[] openImage(String id)
    throws FormatException, IOException;

  /**
   * Allows the client to specify whether or not to separate channels.
   * By default, channels are left unseparated; thus if we encounter an RGB
   * image plane, it will be left as RGB and not split into 3 separate planes.
   */
  public void setSeparated(boolean separate);

  /** Gets whether channels are being separated. */
  public boolean isSeparated();

  /**
   * Gets the rasterized index corresponding
   * to the given Z, C and T coordinates.
   */
  public int getIndex(String id, int z, int c, int t)
    throws FormatException, IOException;

  /**
   * Gets the Z, C and T coordinates corresponding
   * to the given rasterized index value.
   */
  public int[] getZCTCoords(String id, int index)
    throws FormatException, IOException;

  /**
   * Obtains the specified metadata field's value for the given file.
   *
   * @param field the name associated with the metadata field
   * @return the value, or null if the field doesn't exist
   */
  public Object getMetadataValue(String id, String field)
    throws FormatException, IOException;

  /**
   * Obtains the hashtable containing the metadata field/value pairs from
   * the given file.
   *
   * @param id the filename
   * @return the hashtable containing all metadata from the file
   */
  public Hashtable getMetadata(String id) throws FormatException, IOException;
  /**
   * Sets the default metadata store for this reader.
   *
   * @param store a metadata store implementation.
   */
  public void setMetadataStore(MetadataStore store);

  /**
   * Retrieves the current metadata store for this reader. You can be
   * assured that this method will <b>never</b> return a <code>null</code>
   * metadata store.
   * @return a metadata store implementation.
   */
  public MetadataStore getMetadataStore(String id)
    throws FormatException, IOException;

  /**
   * Retrieves the current metadata store's root object. It is guaranteed that
   * all file parsing has been performed by the reader prior to retrieval.
   * Requests for a full populated root object should be made using this method.
   * @param id a fully qualified path to the file.
   * @return current metadata store's root object fully populated.
   * @throws IOException if there is an IO error when reading the file specified
   * by <code>path</code>.
   * @throws FormatException if the file specified by <code>path</code> is of an
   * unsupported type.
   */
  public Object getMetadataStoreRoot(String id)
    throws FormatException, IOException;

  /**
   * A utility method for test reading a file from the command line,
   * and displaying the results in a simple display.
   */
  public boolean testRead(String[] args) throws FormatException, IOException;
}
