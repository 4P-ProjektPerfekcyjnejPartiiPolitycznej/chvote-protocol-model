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
import com.google.common.collect.ImmutableList;
import java.math.BigInteger;
import java.util.List;

/**
 * Model class combining a ballot and a OT query
 */
@SuppressWarnings("unused")
public final class BallotAndQuery {

  private final BigInteger        x_hat;
  private final List<BigIntPair>  bold_a;
  private final NonInteractiveZkp pi;

  @JsonCreator
  public BallotAndQuery(@JsonProperty("x_hat") BigInteger x_hat,
                        @JsonProperty("bold_a") List<BigIntPair> bold_a,
                        @JsonProperty("pi") NonInteractiveZkp pi) {
    this.x_hat = x_hat;
    this.bold_a = ImmutableList.copyOf(bold_a);
    this.pi = pi;
  }

  public BigInteger getX_hat() {
    return x_hat;
  }

  public List<BigIntPair> getBold_a() {
    return bold_a;
  }

  public NonInteractiveZkp getPi() {
    return pi;
  }
}
