/*-------------------------------------------------------------------------------------------------
 - #%L                                                                                            -
 - protocol-model                                                                                 -
 - %%                                                                                             -
 - Copyright (C) 2016 - 2018 République et Canton de Genève                                       -
 - %%                                                                                             -
 - This program is free software: you can redistribute it and/or modify                           -
 - it under the terms of the GNU Affero General Public License as published by                    -
 - the Free Software Foundation, either version 3 of the License, or                              -
 - (at your option) any later version.                                                            -
 -                                                                                                -
 - This program is distributed in the hope that it will be useful,                                -
 - but WITHOUT ANY WARRANTY; without even the implied warranty of                                 -
 - MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the                                   -
 - GNU General Public License for more details.                                                   -
 -                                                                                                -
 - You should have received a copy of the GNU Affero General Public License                       -
 - along with this program. If not, see <http://www.gnu.org/licenses/>.                           -
 - #L%                                                                                            -
 -------------------------------------------------------------------------------------------------*/

package ch.ge.ve.protocol.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Objects;

/**
 * This model abstract class holds the value of a public key
 */
@SuppressWarnings("unused")
public abstract class PublicKey implements Serializable {
  private final BigInteger  key;
  private final CyclicGroup cyclicGroup;

  PublicKey(BigInteger key,
            CyclicGroup cyclicGroup) {
    this.key = key;
    this.cyclicGroup = cyclicGroup;
  }

  public BigInteger getPublicKey() {
    return key;
  }

  public CyclicGroup getCyclicGroup() {
    return cyclicGroup;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PublicKey pk = (PublicKey) o;
    return Objects.equals(key, pk.key) && Objects.equals(cyclicGroup, pk.cyclicGroup);
  }

  @Override
  public int hashCode() {
    return Objects.hash(key, cyclicGroup);
  }
}
