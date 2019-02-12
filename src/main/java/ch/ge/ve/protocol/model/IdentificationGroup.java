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
import com.google.common.base.Preconditions;
import java.math.BigInteger;
import java.util.Objects;

/**
 * The model class representing the identification group
 */
@SuppressWarnings("unused")
public final class IdentificationGroup implements CyclicGroup {
  private final BigInteger p_hat;
  private final BigInteger q_hat;
  private final BigInteger g_hat;

  @JsonCreator
  public IdentificationGroup(
      @JsonProperty("p_hat") BigInteger p_hat,
      @JsonProperty("q_hat") BigInteger q_hat,
      @JsonProperty("g_hat") BigInteger g_hat) {
    Preconditions.checkArgument(q_hat.bitLength() <= p_hat.bitLength());
    Preconditions.checkArgument(g_hat.compareTo(BigInteger.ONE) != 0);
    Preconditions.checkArgument(p_hat.subtract(BigInteger.ONE).mod(q_hat)
                                     .compareTo(BigInteger.ZERO) == 0);
    this.p_hat = p_hat;
    this.q_hat = q_hat;
    this.g_hat = g_hat;
  }

  @Override
  public BigInteger getModulus() {
    return p_hat;
  }

  @Override
  public BigInteger getOrder() {
    return q_hat;
  }

  public BigInteger getP_hat() {
    return p_hat;
  }

  public BigInteger getQ_hat() {
    return q_hat;
  }

  public BigInteger getG_hat() {
    return g_hat;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    IdentificationGroup that = (IdentificationGroup) o;
    return Objects.equals(p_hat, that.p_hat)
        && Objects.equals(q_hat, that.q_hat)
        && Objects.equals(g_hat, that.g_hat);
  }

  @Override
  public int hashCode() {
    return Objects.hash(p_hat, q_hat, g_hat);
  }
}
