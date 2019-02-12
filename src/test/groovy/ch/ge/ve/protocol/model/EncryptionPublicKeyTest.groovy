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

package ch.ge.ve.protocol.model

import com.fasterxml.jackson.databind.ObjectMapper
import spock.lang.Specification

class EncryptionPublicKeyTest extends Specification {

  ObjectMapper objectMapper = new ObjectMapper()

  def "json serialization"() {
    given:
    EncryptionPublicKey key = new EncryptionPublicKey(
            BigInteger.valueOf(3),
            new EncryptionGroup(
                    BigInteger.valueOf(11),
                    BigInteger.valueOf(5),
                    BigInteger.valueOf(3),
                    BigInteger.valueOf(5)
            )
    )

    when:
    def json = objectMapper.writeValueAsString(key)

    then:
    json == '{"publicKey":3,"encryptionGroup":{"p":11,"q":5,"g":3,"h":5}}'
  }

  def "json deserialization"() {
    given:
    String json = '{"publicKey":3,"encryptionGroup":{"p":11,"q":5,"g":3,"h":5}}'

    when:
    def key = objectMapper.readValue(json, EncryptionPublicKey.class)

    then:
    key == new EncryptionPublicKey(
            BigInteger.valueOf(3),
            new EncryptionGroup(
                    BigInteger.valueOf(11),
                    BigInteger.valueOf(5),
                    BigInteger.valueOf(3),
                    BigInteger.valueOf(5)
            )
    )
  }
}
