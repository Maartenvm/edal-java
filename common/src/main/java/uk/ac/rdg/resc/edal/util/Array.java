/*******************************************************************************
 * Copyright (c) 2013 The University of Reading
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 * 3. Neither the name of the University of Reading, nor the names of the
 *    authors or contributors may be used to endorse or promote products
 *    derived from this software without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE AUTHOR ``AS IS'' AND ANY EXPRESS OR
 * IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 * IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY DIRECT, INDIRECT,
 * INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT
 * NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
 * THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 ******************************************************************************/

package uk.ac.rdg.resc.edal.util;

import java.util.Iterator;

/**
 * An multidimensional array of values
 * 
 * @param <E>
 *            the type of the values within the array
 * @author Jon Blower
 * @author Guy
 * 
 * @todo Would a toList() method be useful here?
 */
public interface Array<E> extends Iterable<E> {
    /**
     * Gets the number of dimensions of the array. Equal to getShape().length.
     */
    public int getNDim();

    /**
     * Gets the shape of the array, i.e. the number of points in each direction.
     */
    public int[] getShape();

    /**
     * Returns an iterator over all the values in the array. The convention is
     * that the last dimension (represented by the last entry in getShape())
     * varies fastest.
     */
    @Override
    public Iterator<E> iterator();

    /**
     * Gets a value from the array
     */
    public E get(int... coords);

    /**
     * Sets a value in the array (optional operation)
     * 
     * @throws ArrayIndexOutOfBoundsException
     *             if any of the co-ordinates are beyond the bounds of this
     *             {@link Array}
     */
    public void set(E value, int... coords);

    /**
     * Gets the number of values in the array.
     */
    public long size();
    
    @Override
    public int hashCode();
    
    @Override
    public boolean equals(Object obj);
}
