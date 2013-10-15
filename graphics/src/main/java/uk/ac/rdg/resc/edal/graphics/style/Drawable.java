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

package uk.ac.rdg.resc.edal.graphics.style;

import java.awt.image.BufferedImage;
import java.util.Set;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import uk.ac.rdg.resc.edal.domain.Extent;
import uk.ac.rdg.resc.edal.graphics.style.util.FeatureCatalogue;
import uk.ac.rdg.resc.edal.graphics.style.util.GlobalPlottingParams;

@XmlType(namespace=MapImage.NAMESPACE, name="Drawable")
public abstract class Drawable {
    @XmlTransient
    public class NameAndRange {
        private String fieldLabel;
        private Extent<Float> scaleRange;

        public NameAndRange(String fieldLabel, Extent<Float> scaleRange) {
            super();
            this.fieldLabel = fieldLabel;
            this.scaleRange = scaleRange;
        }

        public String getFieldLabel() {
            return fieldLabel;
        }

        public Extent<Float> getScaleRange() {
            return scaleRange;
        }
    }
    
    @XmlElements({
        @XmlElement(name="FlatOpacity", type = FlatOpacity.class),
        @XmlElement(name="LinearOpacity", type = LinearOpacity.class)
    })
    private OpacityTransform opacityTransform;
    
    public OpacityTransform getOpacityTransform() {
        return opacityTransform;
    }

    @XmlTransient
    public void setOpacityTransform(OpacityTransform opacityTransform) {
        this.opacityTransform = opacityTransform;
    }

    public abstract BufferedImage drawImage(GlobalPlottingParams params, FeatureCatalogue featureCatalogue);
    
    /**
     * This should return a list of all the fields used in this image layer, and
     * their appropriate scale ranges. If there is NO scale range there can be
     * NO data field and vice versa - i.e. a {@link NameAndRange} object must
     * have all non-null fields. If the layer doesn't depend on any data, this
     * should return an empty set.
     * 
     * It should never return <code>null</code>.
     * 
     * @return
     */
    protected abstract Set<NameAndRange> getFieldsWithScales();
}