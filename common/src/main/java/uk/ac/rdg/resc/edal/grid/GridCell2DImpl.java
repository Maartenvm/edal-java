/*******************************************************************************
 * Copyright (c) 2012 The University of Reading
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

package uk.ac.rdg.resc.edal.grid;

import uk.ac.rdg.resc.edal.geometry.Polygon;
import uk.ac.rdg.resc.edal.position.HorizontalPosition;
import uk.ac.rdg.resc.edal.util.GridCoordinates2D;

/**
 * Simple immutable implementation of a GridCell2D.
 * 
 * @author Jon
 * @author Guy
 */
public final class GridCell2DImpl implements GridCell2D {

    private final GridCoordinates2D gridCoords;
    private final HorizontalPosition centre;
    private final Polygon footprint;
    private final HorizontalGrid parentGrid;

    /**
     * @todo check that CRSs of centre, footprint and parentGrid all match?
     */
    public GridCell2DImpl(GridCoordinates2D gridCoords, HorizontalPosition centre, Polygon footprint,
            HorizontalGrid parentGrid) {
        this.gridCoords = gridCoords;
        this.centre = centre;
        this.footprint = footprint;
        this.parentGrid = parentGrid;
    }

    @Override
    public GridCoordinates2D getGridCoordinates() {
        return gridCoords;
    }

    @Override
    public HorizontalPosition getCentre() {
        return centre;
    }

    @Override
    public Polygon getFootprint() {
        return footprint;
    }

    @Override
    public boolean contains(HorizontalPosition position) {
        return footprint.contains(position);
    }

    @Override
    public HorizontalGrid getGrid() {
        return parentGrid;
    }

    @Override
    public String toString() {
        return centre.getX() + "," + centre.getY();
    }
}
