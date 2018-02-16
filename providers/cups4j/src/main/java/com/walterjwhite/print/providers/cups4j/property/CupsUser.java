package com.walterjwhite.print.providers.cups4j.property;

import com.walterjwhite.google.guice.property.property.DefaultValue;
import com.walterjwhite.google.guice.property.property.GuiceProperty;

public interface CupsUser extends GuiceProperty {
  @DefaultValue String Default = null;
}
