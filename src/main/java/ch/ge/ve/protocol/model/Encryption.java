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

import ch.ge.ve.protocol.util.Hashable;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;
import com.google.common.collect.ComparisonChain;
import java.math.BigInteger;
import java.util.Objects;

/**
 * Model class containing one encryption of one ballot
 */
@SuppressWarnings("unused")
public final class Encryption implements Hashable, Comparable<Encryption> {
  private final BigInteger a;
  private final BigInteger b;

  @JsonCreator
  public Encryption(@JsonProperty("a") BigInteger a, @JsonProperty("b") BigInteger b) {
    this.a = a;
    this.b = b;
  }

  public BigInteger getA() {
    return a;
  }

  public BigInteger getB() {
    return b;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Encryption that = (Encryption) o;
    return Objects.equals(a, that.a) && Objects.equals(b, that.b);
  }

  @Override
  public int hashCode() {
    return Objects.hash(a, b);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this).add("a", a).add("b", b).toString();
  }

  @Override
  public Object[] elementsToHash() {
    return new BigInteger[]{a, b};
  }

  @Override
  public int compareTo(Encryption o) {
    return ComparisonChain.start().compare(this.a, o.a).compare(this.b, o.b).result();
  }
}
