// src/components/ui/visually-hidden.tsx

import React from "react";

export const VisuallyHidden: React.FC<{ children: React.ReactNode }> = ({ children }) => (
  <span className="sr-only">
    {children}
  </span>
);
