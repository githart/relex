/*
 * Copyright 2008 Novamente LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package relex.entity;
import java.io.Serializable;

import relex.feature.FeatureNode;

/**
 * This class is just a data-class to store information about an entity
 * that appears in a sentence.  The original sentence is assumed to be
 * a plain string. The index of the first and last position of the
 * entity-identifying substring are stored.  The class may be subclassed
 * to store info about particular entity types such as dates, times,
 * people, etc.
 *
 * The convention is that the lastCharIndex points at the character
 * just after the entity, so that 
 *    lastCharIndex = firstCharIndex + entity.length() 
 * holds true.
 */

public class EntityInfo implements Serializable
{
	private static final long serialVersionUID = 344692623956286950L;
	
	private String originalSentence;

	public String getOriginalSentence() {
		return originalSentence;
	}

	private int firstCharIndex;

	public int getFirstCharIndex() {
		return firstCharIndex;
	}

	private int lastCharIndex;

	public int getLastCharIndex() {
		return lastCharIndex;
	}

	public void setLastCharIndex(int i) {
		lastCharIndex = i;
	}

	public String getOriginalString() {
		return originalSentence.substring(firstCharIndex, lastCharIndex);
	}

	public String idStringPrefix() {
		return "genericID";
	}

	/**
	 * Override to add additional information & markup to 
	 * the feature node for a given word. May be used to
	 * indicate gender, count, etc.
	 */
	protected void setProperties(FeatureNode fn)
	{
		fn.set("ENTITY-FLAG", new FeatureNode("T"));
	}

	public EntityInfo(String _originalSentence,
	                  int _firstCharIndex,
	                  int _lastCharIndex)
	{
		originalSentence = _originalSentence;
		firstCharIndex = _firstCharIndex;
		lastCharIndex = _lastCharIndex;
	}
}
