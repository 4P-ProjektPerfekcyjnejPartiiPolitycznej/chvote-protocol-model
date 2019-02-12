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
 * Model class containing the necessary information for confirmation of the vote
 */
@SuppressWarnings("unused")
public final class Confirmation {
  private final BigInteger        y_hat;
  private final NonInteractiveZkp pi;

  @JsonCreator
  public Confirmation(@JsonProperty("y_hat") BigInteger y_hat,
                      @JsonProperty("pi") NonInteractiveZkp pi) {
    this.y_hat = y_hat;
    this.pi = pi;
  }

  public BigInteger getY_hat() {
    return y_hat;
  }

  public NonInteractiveZkp getPi() {
    return pi;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Confirmation that = (Confirmation) o;
    return Objects.equals(y_hat, that.y_hat)
           && Objects.equals(pi, that.pi);
  }

  @Override
  public int hashCode() {
    return Objects.hash(y_hat, pi);
  }

  @Override
  public String toString() {
    return String.format("Confirmation{y_hat=%s, pi=%s}", y_hat, pi);
  }
}
