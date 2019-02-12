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
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import java.math.BigInteger;
import java.util.Objects;

/**
 * The model class representing the encryption group
 */
@SuppressWarnings("unused")
public final class EncryptionGroup implements CyclicGroup {
  private static final long serialVersionUID = 1L;

  /**
   * Safe prime modulus p
   */
  private final BigInteger p;

  /**
   * Prime order q
   */
  private final BigInteger q;

  /**
   * Generator, independent from h
   */
  private final BigInteger g;

  /**
   * Generator, independent from g
   */
  private final BigInteger h;

  @JsonCreator
  public EncryptionGroup(
      @JsonProperty("p") BigInteger p,
      @JsonProperty("q") BigInteger q,
      @JsonProperty("g") BigInteger g,
      @JsonProperty("h") BigInteger h) {
    Preconditions.checkArgument(q.bitLength() == p.bitLength() - 1);
    Preconditions.checkArgument(g.compareTo(BigInteger.ONE) > 0);
    Preconditions.checkArgument(h.compareTo(BigInteger.ONE) > 0);
    this.p = p;
    this.q = q;
    this.g = g;
    this.h = h;
  }

  @Override
  public BigInteger getModulus() {
    return this.p;
  }

  @Override
  public BigInteger getOrder() {
    return this.q;
  }

  /**
   * Run further precondition checks that are CPU intensive. Use with care so that to not degrade the performances.
   */
  void runIntensivePreconditions() {
    Preconditions.checkArgument(getP().isProbablePrime(100));
    Preconditions.checkArgument(getQ().isProbablePrime(80));
  }

  public BigInteger getP() {
    return p;
  }

  public BigInteger getQ() {
    return q;
  }

  public BigInteger getG() {
    return g;
  }

  public BigInteger getH() {
    return h;
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this).add("p", p).add("q", q).add("g", g).add("h", h).toString();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    EncryptionGroup that = (EncryptionGroup) o;
    return Objects.equals(p, that.p)
        && Objects.equals(q, that.q)
        && Objects.equals(g, that.g)
        && Objects.equals(h, that.h);
  }

  @Override
  public int hashCode() {
    return Objects.hash(p, q, g, h);
  }
}
