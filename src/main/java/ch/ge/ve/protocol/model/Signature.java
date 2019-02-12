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

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigInteger;
import java.util.Objects;

/**
 * Model class representing a message's signature
 */
public class Signature {
  private final BigInteger c;
  private final BigInteger s;

  @JsonCreator
  public Signature(@JsonProperty("c") BigInteger c,
                   @JsonProperty("s") BigInteger s) {
    this.c = c;
    this.s = s;
  }

  public BigInteger getC() {
    return c;
  }

  public BigInteger getS() {
    return s;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Signature that = (Signature) o;
    return c.compareTo(that.c) == 0 &&
           s.compareTo(that.s) == 0;
  }

  @Override
  public int hashCode() {
    return Objects.hash(c, s);
  }

  @Override
  public String toString() {
    return String.format("Signature{c=%s, s=%s}", c, s);
  }
}
