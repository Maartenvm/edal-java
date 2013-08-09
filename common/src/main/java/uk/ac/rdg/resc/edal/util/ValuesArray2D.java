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

public class ValuesArray2D extends Array2D {

    private int[] shape = new int[2];
    private Double[][] data;

    private static final int X_IND = 1;
    private static final int Y_IND = 0;

    public ValuesArray2D(int ySize, int xSize) {
        if (xSize < 1 || ySize < 1) {
            throw new IllegalArgumentException("All dimension sizes must be at least 1");
        }
        shape[X_IND] = xSize;
        shape[Y_IND] = ySize;

        data = new Double[ySize][xSize];
    }

    @Override
    public int[] getShape() {
        return shape;
    }

    @Override
    public Iterator<Number> iterator() {
        return new Iterator<Number>() {
            private int xCounter = 0;
            private int yCounter = 0;

            boolean done = false;

            @Override
            public boolean hasNext() {
                return (!done);
            }

            @Override
            public Number next() {
                Number value = data[yCounter][xCounter];
                /*
                 * Increment the counters if necessary, resetting to zero if
                 * necessary
                 */
                if (xCounter++ >= shape[X_IND]) {
                    xCounter = 0;
                    if (yCounter++ >= shape[Y_IND]) {
                        yCounter = 0;
                        done = true;
                    }
                }
                return value;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException("Remove is not supported for this iterator");
            }
        };
    }

    @Override
    public Number get(int... coords) {
        if (coords.length != 2) {
            throw new IllegalArgumentException("Wrong number of co-ordinates (" + coords.length
                    + ") for this Array (needs 2)");
        }
        return data[coords[Y_IND]][coords[X_IND]];
    }

    @Override
    public void set(Number value, int... coords) {
        if (coords.length != 2) {
            throw new IllegalArgumentException("Wrong number of co-ordinates (" + coords.length
                    + ") for this Array (needs 2)");
        }
        data[coords[Y_IND]][coords[X_IND]] = (value == null) ? null
                : value.doubleValue();
    }

    @Override
    public long size() {
        return shape[X_IND] * shape[Y_IND];
    }
}