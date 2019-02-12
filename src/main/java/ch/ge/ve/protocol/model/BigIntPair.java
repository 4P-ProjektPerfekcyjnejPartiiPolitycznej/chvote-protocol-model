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
import java.math.BigInteger;
import java.util.Objects;

@SuppressWarnings("unused")
public class BigIntPair implements Hashable {
  private final BigInteger left;
  private final BigInteger right;

  @JsonCreator
  public BigIntPair(
      @JsonProperty("left") BigInteger left,
      @JsonProperty("right") BigInteger right) {
    this.left = left;
    this.right = right;
  }

  public BigInteger getLeft() {
    return left;
  }

  public BigInteger getRight() {
    return right;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BigIntPair that = (BigIntPair) o;
    return left.compareTo(that.left) == 0 &&
           right.compareTo(that.right) == 0;
  }

  @Override
  public int hashCode() {
    return Objects.hash(left, right);
  }

  @Override
  public String toString() {
    return String.format("BigIntPair{left=%s, right=%s}", left, right);
  }

  @Override
  public Object[] elementsToHash() {
    return new BigInteger[]{left, right};
  }

  /**
   * Defines the multiplication of BigIntPairs
   *
   * @param that another {@code BigIntPair}
   *
   * @return a new {@code BigIntPair} such that {@code result#left = this#left * that#left} and {@code result#right =
   * this#right * that#right}
   */
  public BigIntPair multiplyValues(BigIntPair that) {
    return new BigIntPair(left.multiply(that.left), right.multiply(that.right));
  }

  /**
   * Take the modulo of each value
   *
   * @param m the modulus
   *
   * @return a new {@code BigIntPair} where each value has been replace by its remainder modulus {@code m}
   */
  public BigIntPair modValues(BigInteger m) {
    return new BigIntPair(left.mod(m), right.mod(m));
  }

  /**
   * utility function to convert to an encryption
   *
   * @return an encryption equivalent to this pair
   */
  public Encryption toEncryption() {
    return new Encryption(left, right);
  }
}
